package com.nagarro.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nagarro.test.dto.AccStmtDto;
import com.nagarro.test.service.AccStmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountStmtController {

    private final AccStmtService accStmtService;

    @RequestMapping(value = "statement", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public <T> ResponseEntity<T> getAccStmt(@RequestParam(value = "account-id", required = true) Long accId,
                                            @RequestParam(value = "from-date", required = false) String fromDate,
                                            @RequestParam(value = "to-date", required = false) String toDate,
                                            @RequestParam(value = "from-amount", required = false) String fromAmount,
                                            @RequestParam(value = "to-amount", required = false) String toAmount) throws Exception {

        AccStmtDto toReturn = accStmtService.getAccountStmt(accId, fromDate, toDate, fromAmount, toAmount);


        if (toReturn == null) {
            ObjectNode respNode = new ObjectMapper().createObjectNode();
            respNode.put("error", "Account Not Found");
            return (ResponseEntity<T>) new ResponseEntity<String>(respNode.toString(), HttpStatus.NOT_FOUND);
        }

        if (toReturn.getStatement() == null || toReturn.getStatement().isEmpty()) {
            ObjectNode respNode = new ObjectMapper().createObjectNode();
            respNode.put("error", "Account Statement Not Found");
            return (ResponseEntity<T>) new ResponseEntity<String>(respNode.toString(), HttpStatus.NOT_FOUND);
        }

        return (ResponseEntity<T>) new ResponseEntity<AccStmtDto>(toReturn, HttpStatus.OK);
    }


}
