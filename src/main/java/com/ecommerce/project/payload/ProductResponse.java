package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Paginated response containing products and pagination information")
public class ProductResponse {

    @Schema(
        description = "Products available on the current page"
    )
    private List<ProductDTO> content;

    @Schema(
        description = "Current page number, starting from 0",
        example = "0"
    )
    private Integer pageNumber;

    @Schema(
        description = "Maximum number of products per page",
        example = "10"
    )
    private Integer pageSize;

    @Schema(
        description = "Total number of products across all pages",
        example = "100"
    )
    private Long totalElements;

    @Schema(
        description = "Total number of pages",
        example = "10"
    )
    private Integer totalPages;

    @Schema(
        description = "Indicates whether the current page is the last page",
        example = "false"
    )
    private boolean lastPage;
}