package com.icodeap.ecommerce.catalog.application.port.in;

import com.icodeap.ecommerce.catalog.domain.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
