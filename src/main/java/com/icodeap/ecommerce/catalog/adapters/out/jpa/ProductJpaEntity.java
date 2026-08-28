package com.icodeap.ecommerce.catalog.adapters.out.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String category;

    @Column(nullable = false)
    private BigDecimal priceAmount;

    @Column(nullable = false)
    private boolean active;

    protected ProductJpaEntity() {
    }

    public ProductJpaEntity(String id, String name, String category, BigDecimal priceAmount, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.priceAmount = priceAmount;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public boolean isActive() {
        return active;
    }
}
