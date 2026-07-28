package com.icodeap.ecommerce.catalog.adapters.in.controller;

import com.icodeap.ecommerce.catalog.application.port.in.GetProductByIdUseCase;
import com.icodeap.ecommerce.catalog.application.port.in.ListProductsUseCase;
import com.icodeap.ecommerce.catalog.application.service.ProductNotFoundException;
import com.icodeap.ecommerce.catalog.domain.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogController.class)
class CatalogExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ListProductsUseCase listProductsUseCase;

    @MockitoBean
    private GetProductByIdUseCase getProductByIdUseCase;

    @Test
    void returnsNotFoundBodyWhenProductDoesNotExist() throws Exception {
        when(getProductByIdUseCase.getById("missing")).thenThrow(new ProductNotFoundException("missing"));

        mockMvc.perform(get("/api/v1/catalog/products/missing"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").value("Product not found"))
            .andExpect(jsonPath("$.message").value("Product not found: missing"));
    }

    @Test
    void returnsBadRequestBodyWhenBusinessRuleIsViolated() throws Exception {
        when(getProductByIdUseCase.getById("bad-price"))
            .thenThrow(new BusinessRuleViolationException("amount must not be negative"));

        mockMvc.perform(get("/api/v1/catalog/products/bad-price"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Invalid product data"))
            .andExpect(jsonPath("$.message").value("amount must not be negative"));
    }
}
