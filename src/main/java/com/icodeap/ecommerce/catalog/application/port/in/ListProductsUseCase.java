package com.icodeap.ecommerce.catalog.application.port.in;


import com.icodeap.ecommerce.catalog.domain.Product;

import java.util.List;

public interface ListProductsUseCase {
    List<Product> list();
}