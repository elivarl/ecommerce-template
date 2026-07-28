package com.icodeap.ecommerce.catalog.domain;

public record Product(ProductId id, String name, String category, Money price, boolean active) {

    public Product {
        if (name == null || name.isBlank()) {
            throw new BusinessRuleViolationException("name must not be blank");
        }
    }
}