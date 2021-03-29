package com.credit.demo.domain;

public class CustomerCreditScore {
    private long customerId;
    private int creditScore;

    public CustomerCreditScore(long customerId, int creditScore) {
        this.customerId = customerId;
        this.creditScore = creditScore;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
