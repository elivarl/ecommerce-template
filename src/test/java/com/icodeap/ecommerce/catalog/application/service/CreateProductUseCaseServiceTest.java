package com.icodeap.ecommerce.catalog.application.service;

import com.icodeap.ecommerce.catalog.application.port.in.CreateProductCommand;
import com.icodeap.ecommerce.catalog.application.port.out.ProductRepository;
import com.icodeap.ecommerce.catalog.domain.Product;
import com.icodeap.ecommerce.catalog.domain.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateProductUseCaseServiceTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final CreateProductUseCaseService useCase = new CreateProductUseCaseService(repository);

    @Test
    void createsAndPersistsNewProduct() {
        CreateProductCommand command = new CreateProductCommand(
            "p-200", "Webcam", "Accessories", new BigDecimal("59.90"), true);

        when(repository.existsById(new ProductId("p-200"))).thenReturn(false);
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product created = useCase.create(command);

        assertThat(created.id().value()).isEqualTo("p-200");
        assertThat(created.name()).isEqualTo("Webcam");
        assertThat(created.price().amount()).isEqualByComparingTo("59.90");
        verify(repository).save(any(Product.class));
    }

    @Test
    void rejectsCreationWhenIdAlreadyExists() {
        CreateProductCommand command = new CreateProductCommand(
            "p-100", "Wireless Mouse", "Accessories", new BigDecimal("25.90"), true);

        when(repository.existsById(new ProductId("p-100"))).thenReturn(true);

        assertThatThrownBy(() -> useCase.create(command))
            .isInstanceOf(ProductAlreadyExistsException.class)
            .hasMessage("Product already exists: p-100");

        verify(repository, never()).save(any(Product.class));
    }
}
