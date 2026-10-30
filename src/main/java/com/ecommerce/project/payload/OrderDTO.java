package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order details including products, payment and delivery address")
public class OrderDTO {

    @Schema(
        description = "Unique ID of the order",
        example = "2001",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long orderId;

    @Schema(
        description = "Email address of the customer who placed the order",
        example = "customer@example.com"
    )
    private String email;

    @Schema(
        description = "List of items included in the order"
    )
    private List<OrderItemDTO> orderItems;

    @Schema(
        description = "Date on which the order was placed",
        example = "2026-10-27",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDate orderDate;

    @Schema(
        description = "Payment details associated with the order"
    )
    private PaymentDTO payment;

    @Schema(
        description = "Total amount of the order",
        example = "49999.00"
    )
    private Double totalAmount;

    @Schema(
        description = "Current status of the order",
        example = "CONFIRMED"
    )
    private String orderStatus;

    @Schema(
        description = "ID of the delivery address associated with the order",
        example = "101"
    )
    private Long addressId;
}