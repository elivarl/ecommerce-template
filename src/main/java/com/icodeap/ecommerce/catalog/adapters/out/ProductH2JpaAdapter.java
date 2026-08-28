package com.icodeap.ecommerce.catalog.adapters.out;

import com.icodeap.ecommerce.catalog.adapters.out.jpa.ProductJpaEntity;
import com.icodeap.ecommerce.catalog.adapters.out.jpa.ProductSpringDataRepository;
import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Money;
import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile({"h2", "default"})
public class ProductH2JpaAdapter implements ProductRepository {

    private final ProductSpringDataRepository springDataRepository;

    public ProductH2JpaAdapter(ProductSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public boolean existsById(ProductId id) {
        return springDataRepository.existsById(id.value());
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity(
            product.id().value(),
            product.name(),
            product.category(),
            product.price().amount(),
            product.active()
        );

        ProductJpaEntity saved = springDataRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Product> findAll() {
        return springDataRepository.findAll().stream()
            .map(this::toDomain)
            .toList();
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return springDataRepository.findById(id.value())
            .map(this::toDomain);
    }

    private Product toDomain(ProductJpaEntity entity) {
        return new Product(
            new ProductId(entity.getId()),
            entity.getName(),
            entity.getCategory(),
            new Money(entity.getPriceAmount()),
            entity.isActive()
        );
    }
}
