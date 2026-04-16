package com.icodeap.ecommerce.catalog.domain;

public record Product(ProductId id, String name, String category, Money price, boolean active) {
}