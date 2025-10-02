package com.example.commons.dto.create;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for Product creation and update requests
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String id;

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Product description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Product price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal price;

    @NotNull(message = "Product quantity is required")
    @Min(value = 0, message = "Product quantity cannot be negative")
    private Integer quantity;

    @NotBlank(message = "Product category is required")
    @Pattern(regexp = "Electronics|Clothing|Books|Home|Sports",
            message = "Category must be one of: Electronics, Clothing, Books, Home, Sports")
    private String category;

}
