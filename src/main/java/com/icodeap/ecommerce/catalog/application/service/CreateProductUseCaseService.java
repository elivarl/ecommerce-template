package com.icodeap.ecommerce.catalog.application.service;

import com.icodeap.ecommerce.catalog.application.port.in.CreateProductCommand;
import com.icodeap.ecommerce.catalog.application.port.in.CreateProductUseCase;
import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Money;
import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCaseService implements CreateProductUseCase {

    private final ProductRepository repository;

    public CreateProductUseCaseService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(CreateProductCommand command) {
        ProductId id = new ProductId(command.id());
        if (repository.existsById(id)) {
            throw new ProductAlreadyExistsException(command.id());
        }

        Product product = new Product(
            id,
            command.name(),
            command.category(),
            new Money(command.priceAmount()),
            command.active()
        );

        return repository.save(product);
    }
}
