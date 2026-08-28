package com.icodeap.ecommerce.catalog.application.port.out;

import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    boolean existsById(ProductId id);
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(ProductId id);
}
