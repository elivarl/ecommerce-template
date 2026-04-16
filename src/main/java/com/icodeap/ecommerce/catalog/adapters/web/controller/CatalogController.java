package com.icodeap.ecommerce.catalog.adapters.web.controller;

import com.icodeap.ecommerce.catalog.adapters.web.dto.ProductResponse;
import com.icodeap.ecommerce.catalog.application.port.in.GetProductByIdUseCase;
import com.icodeap.ecommerce.catalog.application.port.in.ListProductsUseCase;
import com.icodeap.ecommerce.catalog.domain.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog/products")
@AllArgsConstructor
public class CatalogController {

    private final ListProductsUseCase listProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;


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