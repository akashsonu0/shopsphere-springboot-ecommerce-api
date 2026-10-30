package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard API response containing a message and status")
public class APIResponse {

    @Schema(
        description = "Message describing the API operation result",
        example = "Category deleted successfully"
    )
    public String message;

    @Schema(
        description = "Indicates whether the operation was successful",
        example = "true"
    )
    private boolean status;
}