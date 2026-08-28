package com.icodeap.ecommerce.catalog.adapters.out;

import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Money;
import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("memory")
public class ProductInMemoryRepository implements ProductRepository {

    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public ProductInMemoryRepository() {
        // Datos de prueba iniciales en memoria
        save(new Product(new ProductId("p-100"), "Wireless Mouse", "Accessories", new Money(new BigDecimal("25.90")), true));
        save(new Product(new ProductId("p-101"), "Mechanical Keyboard", "Accessories", new Money(new BigDecimal("89.00")), true));
        save(new Product(new ProductId("p-102"), "USB-C Hub", "Peripherals", new Money(new BigDecimal("45.50")), false));
        save(new Product(new ProductId("p-103"), "Laptop Stand", "Office", new Money(new BigDecimal("32.00")), true));
        save(new Product(new ProductId("p-104"), "Noise Cancelling Headphones", "Audio", new Money(new BigDecimal("199.99")), true));
    }

    @Override
    public boolean existsById(ProductId id) {
        return products.containsKey(id.value());
    }

    @Override
    public Product save(Product product) {
        products.put(product.id().value(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return Optional.ofNullable(products.get(id.value()));
    }
}