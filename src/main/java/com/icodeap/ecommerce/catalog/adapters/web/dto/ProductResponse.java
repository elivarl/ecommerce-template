package com.icodeap.ecommerce.catalog.adapters.web.dto;

import java.math.BigDecimal;

public record ProductResponse(
    String id,
    String name,
    String category,
    BigDecimal priceAmount,
    boolean active
) {
}