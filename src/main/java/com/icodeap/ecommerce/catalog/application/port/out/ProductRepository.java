package com.icodeap.ecommerce.catalog.application.port.out;

import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(ProductId id);
}