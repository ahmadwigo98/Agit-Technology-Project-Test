package com.agit.demo.repository;

import com.agit.demo.model.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerAccount, Long> {

    CustomerAccount findByAccountNumber(String accountNumber);
}
