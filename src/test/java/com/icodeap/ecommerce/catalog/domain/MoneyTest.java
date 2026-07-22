package com.icodeap.ecommerce.catalog.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    void createsMoneyWithNonNegativeAmount() {
        Money money = new Money(BigDecimal.TEN);

        assertThat(money.amount()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void rejectsNullAmount() {
        assertThatThrownBy(() -> new Money(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must not be null");
    }

    @Test
    void rejectsNegativeAmount() {
        assertThatThrownBy(() -> new Money(BigDecimal.valueOf(-1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must not be negative");
    }
}
