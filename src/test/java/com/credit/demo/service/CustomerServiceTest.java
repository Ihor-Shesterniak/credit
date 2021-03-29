package com.credit.demo.service;

import com.credit.demo.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Test
    public void getCreditScore() {
        LocalDate dateOfBirth = LocalDate.of(1981, 10, 11);
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Ken");
        customer.setAge(40);
        long mills = dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        customer.setDateOfBirth(new Date(mills));
        customer.setGrossAnnualIncome(40_000L);
        assertEquals(599, service.getCreditScore(customer).getCreditScore());
    }

    @Test
    public void calculateCreditScore() {
        assertEquals(399, service.calculateCreditScore(20, 40_000L));
        assertEquals(599, service.calculateCreditScore(30, 50_000L));
        assertEquals(799, service.calculateCreditScore(40, 70_000L));
        assertEquals(999, service.calculateCreditScore(50, 90_000L));
    }

}

