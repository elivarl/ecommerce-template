package com.icodeap.ecommerce.catalog.adapters.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateProductRequest(
    @NotBlank String id,
    @NotBlank String name,
    String category,
    @NotNull @PositiveOrZero BigDecimal priceAmount,
    boolean active
) {
}
