package com.agit.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerService {

    Map<String, Object> checkBalance (String accountNumber);
    Map<String, Object> transferToAccount(String fromAccountNumber, String toAccountNumber, int amount);
}
