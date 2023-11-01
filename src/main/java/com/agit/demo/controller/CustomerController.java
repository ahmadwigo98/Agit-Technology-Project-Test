package com.agit.demo.controller;

import com.agit.demo.model.CustomerAccount;
import com.agit.demo.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "account")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{account_number}")
    public ResponseEntity<Map<String, Object>> checkBalance (@PathVariable("account_number") String accountNumber) {
        Map<String, Object> response = customerService.checkBalance(accountNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/{from_account_number}/transfer")
    public ResponseEntity<Map<String, Object>> doTransfer (@PathVariable("from_account_number") String fromAccountNumber,
                                                           @RequestBody Map<String, Object> request) {
        String toAccountNumber = (String) request.get("to_account_number");
        Integer amount = (Integer) request.get("amount");
        Map<String, Object> response = customerService.transferToAccount(fromAccountNumber, toAccountNumber, amount);

        return response.isEmpty() ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(response, HttpStatus.OK);
    }
}
