package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Product details")
public class ProductDTO {

    @Schema(description = "Unique product ID",
            example = "501",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long productId;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    @Schema(description = "Name of the product",
            example = "Smartphone",
            minLength = 3,
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String productName;

    @Schema(description = "Product image path or URL",
            example = "default.png")
    private String image;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    @Schema(description = "Description of the product",
            example = "Smartphone with an advanced camera",
            minLength = 6,
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Schema(description = "Available quantity",
            example = "25",
            minimum = "0",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

    @Positive(message = "Price must be greater than zero")
    @Schema(description = "Original price of the product",
            example = "1000.00",
            exclusiveMinimum = true,
            requiredMode = Schema.RequiredMode.REQUIRED)
    private double price;

    @PositiveOrZero(message = "Discount cannot be negative")
    @Max(value = 100, message = "Discount cannot exceed 100 percent")
    @Schema(description = "Discount percentage, between 0 and 100",
            example = "20.0",
            minimum = "0",
            maximum = "100",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private double discount;

    @Schema(description = "Calculated selling price after discount",
            example = "800.00",
            accessMode = Schema.AccessMode.READ_ONLY)
    private double specialPrice;
}