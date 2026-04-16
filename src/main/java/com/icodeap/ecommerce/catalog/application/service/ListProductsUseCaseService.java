package com.icodeap.ecommerce.catalog.application.service;

import com.icodeap.ecommerce.catalog.application.port.in.ListProductsUseCase;
import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductsUseCaseService implements ListProductsUseCase {

    private final ProductRepository repository;

    public ListProductsUseCaseService(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Product> list() {
        return repository.findAll().stream().filter(Product::active).toList();
    }
}