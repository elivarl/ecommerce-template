package com.icodeap.ecommerce.catalog.adapters.in.controller;

import com.icodeap.ecommerce.catalog.adapters.in.dto.CreateProductRequest;
import com.icodeap.ecommerce.catalog.adapters.in.dto.ProductResponse;
import com.icodeap.ecommerce.catalog.application.port.in.CreateProductCommand;
import com.icodeap.ecommerce.catalog.application.port.in.CreateProductUseCase;
import com.icodeap.ecommerce.catalog.application.port.in.GetProductByIdUseCase;
import com.icodeap.ecommerce.catalog.application.port.in.ListProductsUseCase;
import com.icodeap.ecommerce.catalog.domain.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog/products")
@AllArgsConstructor
public class CatalogController {

    private final ListProductsUseCase listProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final CreateProductUseCase createProductUseCase;


    @GetMapping
    public List<ProductResponse> list() {
        return listProductsUseCase.list().stream()
            .map(this::toResponse)
            .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable String id) {
        return toResponse(getProductByIdUseCase.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        CreateProductCommand command = new CreateProductCommand(
            request.id(),
            request.name(),
            request.category(),
            request.priceAmount(),
            request.active()
        );

        return toResponse(createProductUseCase.create(command));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
            product.id().value(),
            product.name(),
            product.category(),
            product.price().amount(),
            product.active()
        );
    }
}