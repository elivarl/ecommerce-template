package com.icodeap.ecommerce.catalog.application.port.in;


import com.icodeap.ecommerce.catalog.domain.Product;

public interface GetProductByIdUseCase {
    Product getById(String id);
}