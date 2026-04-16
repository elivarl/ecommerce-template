package com.icodeap.ecommerce.catalog.adapters.persistence;

import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Money;
import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = List.of(
        new Product(new ProductId("p-100"), "Wireless Mouse", "Accessories", new Money(new BigDecimal("25.90")), true),
        new Product(new ProductId("p-101"), "Mechanical Keyboard", "Accessories", new Money(new BigDecimal("89.00")), true),
        new Product(new ProductId("p-102"), "USB-C Hub", "Peripherals", new Money(new BigDecimal("45.50")), false),
        new Product(new ProductId("p-103"), "Laptop Stand", "Office", new Money(new BigDecimal("32.00")), true),
        new Product(new ProductId("p-104"), "Noise Cancelling Headphones", "Audio", new Money(new BigDecimal("199.99")), true)
    );

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return products.stream()
            .filter(product -> product.id().value().equals(id.value()))
            .findFirst();
    }

}