package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

//Swagger Annotation
@Tag(
	    name = "Address APIs",
	    description = "APIs for managing user addresses"
	)

public class AddressController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    AddressService addressService;
    
    // Swagger Annotation
    @Operation(summary = "Create an address" , description = "Creates a new address for the currently logged-in user.")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Address created successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid address data" , content = @Content),
    	@ApiResponse(responseCode = "401", description = "User is not authenticated" , content = @Content)
	})

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO, user);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }
    
    // Swagger Annotation
    @Operation(summary = "Get all addresses" , description = "Retrieves the list of addresses.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Address retrieved  successfully"),
    	@ApiResponse(responseCode = "404", description = "Address not found" , content = @Content),
	})

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){
        List<AddressDTO> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Get address by ID" , description = "Retrieves an address using its address ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Address retrieved  successfully"),
    	@ApiResponse(responseCode = "404", description = "Address not found" , content = @Content),
	})

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(
    		// Swagger Annotation
    		@Parameter(description = "ID of the address to retrieve", example = "1")
    		
    		@PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddressesById(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    // Swagger Annotation
    @Operation(summary = "Get logged-in user's addresses" , description = "Retrieves the addresses associated with the currently logged-in user.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "User addresses retrieved successfully"),
    	@ApiResponse(responseCode = "401", description = "User is not authenticated" , content = @Content),
	})
    
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddresses(user);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Update an address" , description = "Updates the address details for the specified address ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Address updated successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid address data" , content = @Content),
    	@ApiResponse(responseCode = "404", description = "Address not found" , content = @Content)
	})

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(
    		// Swagger Annotation
    		@Parameter(description = "ID of the address to update", example = "1")
    		
    		@PathVariable Long addressId, 
    		@RequestBody AddressDTO addressDTO){
        AddressDTO updatedAddress = addressService.updateAddress(addressId, addressDTO);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Delete an address" , description = "Deletes the address associated with the specified address ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Address deleted successfully"),
    	@ApiResponse(responseCode = "404", description = "Address not found" , content = @Content)
	})

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress(
    		// Swagger Annotation
    		@Parameter(description = "ID of the address to delete", example = "1")
    		
    		@PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
