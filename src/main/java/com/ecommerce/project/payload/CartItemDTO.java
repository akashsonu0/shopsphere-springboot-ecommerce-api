package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Details of an individual item in a shopping cart")
public class CartItemDTO {

    @Schema(
        description = "Unique ID of the cart item",
        example = "1001",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long cartItemId;

    @Schema(
        description = "Shopping cart to which this item belongs"
    )
    private CartDTO cart;

    @Schema(
        description = "Product associated with this cart item"
    )
    private ProductDTO productDTO;

    @Schema(
        description = "Quantity of the product in the cart",
        example = "2"
    )
    private Integer quantity;

    @Schema(
        description = "Discount applied to the product",
        example = "10.0"
    )
    private Double discount;

    @Schema(
        description = "Price of the product used for this cart item",
        example = "24999.00"
    )
    private Double productPrice;
}