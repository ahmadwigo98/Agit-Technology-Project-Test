package com.agit.demo.service.implementation;

import com.agit.demo.model.CustomerAccount;
import com.agit.demo.repository.CustomerRepository;
import com.agit.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.agit.demo.utils.MessageConstant.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Map<String, Object> checkBalance(String accountNumber) {
        CustomerAccount customerAccount = customerRepository.findByAccountNumber(accountNumber);

        Map<String, Object> map = new HashMap<>();
        if (customerAccount == null) map.put("message", ACCOUNT_NOT_FOUND);
        else {
            map.put("account_number", customerAccount.getAccountNumber());
            map.put("customer_name”", customerAccount.getCustomerName());
            map.put("balance", customerAccount.getBalance());
        }

        return map;
    }

    @Override
    public Map<String, Object> transferToAccount(String fromAccountNumber, String toAccountNumber, int amount) {
        CustomerAccount fromAccount = customerRepository.findByAccountNumber(fromAccountNumber);
        CustomerAccount toAccount = customerRepository.findByAccountNumber(toAccountNumber);

        Map<String, Object> map = new HashMap<>();
        if (fromAccountNumber.equals(toAccountNumber)) map.put("message", SAME_ACCOUNT_TRANSFER_VIOLATION);
        else if (fromAccount == null) map.put("message", FROM_ACCOUNT_NOT_FOUND);
        else if (toAccount == null) map.put("message", TO_ACCOUNT_NOT_FOUND);
        else if (Objects.requireNonNull(fromAccount).getBalance() < amount) map.put("message", BALANCE_INSUFFICIENT);
        else {
            fromAccount.transfer(toAccount, amount);
            customerRepository.save(fromAccount);
            customerRepository.save(toAccount);
        }

        return map;
    }
}
