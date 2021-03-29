package com.credit.demo.service;

import com.credit.demo.domain.Customer;
import com.credit.demo.domain.CustomerCreditScore;
import com.credit.demo.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

@Service
public class CustomerService {

    private static final int MAX_CREDIT_SCORE = 999;

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public CustomerCreditScore getCreditScore(Customer customer) {
        if (customer.getId() == null) {
            requireNonNull(customer.getFirstName(), "first name is required");
            requireNonNull(customer.getLastName(), "last name is required");
            requireNonNull(customer.getAge(), "age is required");
            requireNonNull(customer.getDateOfBirth(), "date of  birth is required");
            requireNonNull(customer.getGrossAnnualIncome(), "annual income is required");

            int creditScore = calculateCreditScore(customer.getAge(), customer.getGrossAnnualIncome());
            customer.setCreditScore(creditScore);
            repository.save(customer);
            return new CustomerCreditScore(customer.getId(), creditScore);
        }

        Customer foundCustomer = repository.findById(customer.getId())
                .orElse(null);
        if (foundCustomer == null) {
            throw new IllegalArgumentException("Customer with id " + customer.getId() + " not found");
        }

        return new CustomerCreditScore(foundCustomer.getId(), foundCustomer.getCreditScore());
    }

    public int calculateCreditScore(int age, Long income) {
        double agePercent = 0;
        double incomePercent = 0;
        if (age < 20) {
            agePercent = 0.1;
        } else if (age < 30) {
            agePercent = 0.2;
        } else if (age < 40) {
            agePercent = 0.3;
        } else if (age < 50) {
            agePercent = 0.4;
        } else {
            agePercent = 0.5;
        }

        if (income < 30_000) {
            incomePercent = 0.1;
        } else if (income < 50_000) {
            incomePercent = 0.2;
        } else if (income < 70_000) {
            incomePercent = 0.3;
        } else if (income < 90_000) {
            incomePercent = 0.4;
        } else {
            incomePercent = 0.5;
        }

        return (int) (MAX_CREDIT_SCORE * (incomePercent + agePercent));
    }
}
