package com.icodeap.ecommerce.catalog.adapters.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpringDataRepository extends JpaRepository<ProductJpaEntity, String> {
}
