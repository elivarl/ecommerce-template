package com.icodeap.ecommerce.catalog.domain;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    public Money {
        if (amount == null) {
            throw new BusinessRuleViolationException("amount must not be null");
        }
        if (amount.signum() < 0) {
            throw new BusinessRuleViolationException("amount must not be negative");
        }
    }
}