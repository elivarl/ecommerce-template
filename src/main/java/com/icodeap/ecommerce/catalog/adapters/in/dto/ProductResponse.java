package com.icodeap.ecommerce.catalog.adapters.in.dto;

import java.math.BigDecimal;

public record ProductResponse(
    String id,
    String name,
    String category,
    BigDecimal priceAmount,
    boolean active
) {
}