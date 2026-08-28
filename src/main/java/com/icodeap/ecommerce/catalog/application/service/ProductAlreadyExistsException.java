package com.icodeap.ecommerce.catalog.application.service;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String id) {
        super("Product already exists: " + id);
    }
}
