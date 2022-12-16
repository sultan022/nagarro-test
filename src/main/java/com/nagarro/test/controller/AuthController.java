package com.nagarro.test.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nagarro.test.dto.LoginRequest;
import com.nagarro.test.dto.MessageResponse;
import com.nagarro.test.dto.SignupRequest;
import com.nagarro.test.dto.UserInfoResponse;
import com.nagarro.test.security.JwtUtils;
import com.nagarro.test.service.UserDetailsImpl;
import com.nagarro.test.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * logs in the user, returns a jwt token in cookie, mark the user as logged in db
     * */
    @PostMapping("login")
    public <T> ResponseEntity<T> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (userDetails.getLoginStatus().booleanValue()) {
            ObjectNode respNode = new ObjectMapper().createObjectNode();
            respNode.put("error", userDetails.getUsername() + " already logged in");
            return (ResponseEntity<T>) ResponseEntity.badRequest().body(respNode.toString());
        }


        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        CompletableFuture.runAsync(() -> {
            userDetailsServiceImpl.changeUserLoginStatus(userDetails.getId(), Boolean.TRUE);

        });


        return (ResponseEntity<T>) ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        roles));
    }

    /**
     * logout the user by clearing cookie and mark the user as logged out in db
     * */
    @PostMapping(value = "logout/{usr_id}")
    public <T> ResponseEntity<T> logoutUser(@PathVariable(value = "usr_id", required = true) Long userId) {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        boolean updated = userDetailsServiceImpl.changeUserLoginStatus(userId, Boolean.FALSE);
        ObjectNode respNode = new ObjectMapper().createObjectNode();
        if (updated) {

            respNode.put("error", "You've been signed out!");
            return (ResponseEntity<T>) ResponseEntity.ok().
                    header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(respNode.toString());
        } else {
            return ResponseEntity.notFound().
                    header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .build();
        }

    }

    /**
     * only to show decrypted password
     * */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        String pass = encoder.encode(signUpRequest.getPassword());
        log.info("encrypted pass {} " + pass);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}




