package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Details of a product included in an order")
public class OrderItemDTO {

    @Schema(
        description = "Unique ID of the order item",
        example = "3001",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long orderItemId;

    @Schema(
        description = "Product details associated with the order item"
    )
    private ProductDTO product;

    @Schema(
        description = "Quantity of the product ordered",
        example = "2"
    )
    private Integer quantity;

    @Schema(
        description = "Discount applied to the ordered product",
        example = "10.0"
    )
    private double discount;

    @Schema(
        description = "Price of the product when it was ordered",
        example = "24999.00"
    )
    private double orderedProductPrice;
}