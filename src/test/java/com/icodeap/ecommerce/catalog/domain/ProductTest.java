package com.icodeap.ecommerce.catalog.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    void createsProductWithNonBlankName() {
        Product product = new Product(
                new ProductId("p-1"), "Keyboard", "electronics", new Money(BigDecimal.TEN), true);

        assertThat(product.name()).isEqualTo("Keyboard");
    }

    @Test
    void rejectsNullName() {
        assertThatThrownBy(() -> new Product(
                new ProductId("p-1"), null, "electronics", new Money(BigDecimal.TEN), true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name must not be blank");
    }

    @Test
    void rejectsBlankName() {
        assertThatThrownBy(() -> new Product(
                new ProductId("p-1"), "   ", "electronics", new Money(BigDecimal.TEN), true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name must not be blank");
    }
}
