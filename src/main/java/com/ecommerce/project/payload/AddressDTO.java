package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Address details associated with a user")
public class AddressDTO {

    @Schema(
        description = "Unique ID of the address",
        example = "101",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long addressId;

    @Schema(
        description = "Street name or street address",
        example = "MG Road"
    )
    private String street;

    @Schema(
        description = "Building or apartment name",
        example = "Sunrise Apartments"
    )
    private String buildingName;

    @Schema(
        description = "City where the address is located",
        example = "Bengaluru"
    )
    private String city;

    @Schema(
        description = "State where the address is located",
        example = "Karnataka"
    )
    private String state;

    @Schema(
        description = "Country where the address is located",
        example = "India"
    )
    private String country;

    @Schema(
        description = "Postal PIN code",
        example = "560001"
    )
    private String pincode;
}