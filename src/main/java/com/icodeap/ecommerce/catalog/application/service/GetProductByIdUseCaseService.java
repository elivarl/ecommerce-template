package com.icodeap.ecommerce.catalog.application.service;

import com.icodeap.ecommerce.catalog.application.port.in.GetProductByIdUseCase;
import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUseCaseService implements GetProductByIdUseCase {

    private final ProductRepository repository;

    public GetProductByIdUseCaseService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getById(String id) {
        return  repository.findById(new ProductId(id)).filter(Product::active)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}