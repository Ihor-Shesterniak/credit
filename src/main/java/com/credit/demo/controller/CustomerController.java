package com.credit.demo.controller;

import com.credit.demo.domain.Customer;
import com.credit.demo.domain.CustomerCreditScore;
import com.credit.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/creditscore")
    public CustomerCreditScore getCreditScore(@RequestBody Customer customer) {
        return customerService.getCreditScore(customer);
    }

}
