package com.credit.demo.repo;

import com.credit.demo.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;
    private Customer customer;
    private Customer savedCustomer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Ken");
        customer.setAge(40);
        customer.setDateOfBirth(new Date());
        customer.setGrossAnnualIncome(50_000L);
        savedCustomer = repository.save(customer);
    }

    @Test
    public void addNewCustomer() {
        assertNotNull(savedCustomer);
    }

    @Test
    public void findCustomerById() {
        Optional<Customer> customerById = repository.findById(customer.getId());
        assertEquals(customer.getId(), customerById.get().getId());
    }
}