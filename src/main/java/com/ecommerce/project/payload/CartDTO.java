package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Shopping cart details including products and total price")
public class CartDTO {

    @Schema(
        description = "Unique ID of the shopping cart",
        example = "501",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long cartId;

    @Schema(
        description = "Total price of all products in the cart",
        example = "49999.00"
    )
    private Double totalPrice = 0.0;

    @Schema(
        description = "List of products present in the shopping cart"
    )
    private List<ProductDTO> products = new ArrayList<>();
}