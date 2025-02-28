package com.ragheb.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

        @NotNull(message = "product name is required")
        String name,

        @NotNull(message = "product description is required")
        String description,

        @Positive(message = "product available quantity must be greater than 0")
        double availableQuantity,

        @Positive(message = "product price must be greater than 0")
        BigDecimal price,

        @NotNull(message = "product category is required")
        Integer categoryId
) {
}
