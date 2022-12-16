package com.nagarro.test.service;

import com.nagarro.test.entity.UserEntity;
import com.nagarro.test.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));


        return UserDetailsImpl.build(user);
    }


    @Transactional
    public Boolean changeUserLoginStatus(Long userId, Boolean status) {

        if (userRepository.existsById(userId)){
            userRepository.changeUserLoginStatus(userId, status);
            return Boolean.TRUE;
        }

        else return Boolean.FALSE;
    }


}