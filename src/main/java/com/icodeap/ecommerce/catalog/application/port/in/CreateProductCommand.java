package com.icodeap.ecommerce.catalog.application.port.in;

import java.math.BigDecimal;

public record CreateProductCommand(
    String id,
    String name,
    String category,
    BigDecimal priceAmount,
    boolean active
) {
}
