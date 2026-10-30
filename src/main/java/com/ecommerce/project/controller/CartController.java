package com.ecommerce.project.controller;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.payload.CartDTO;
import com.ecommerce.project.repositories.CartRepository;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.util.AuthUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

//Swagger Annotation
@Tag(
		name = "Cart APIs",
	    description = "APIs for adding, viewing, updating and removing cart products"
	)

public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private CartService cartService;
    
    // Swagger Annotation
    @Operation(summary = "Add product to cart" , description = "Adds a product to the cart with the specified quantity.")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Product added to cart successfully"),
    	@ApiResponse(responseCode = "404", description = "Product not found" , content = @Content)
	})


    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(
    		// Swagger Annotation
    		@Parameter(description = "ID of the product to add", example = "1")
   
    		@PathVariable Long productId,
    		
    		// Swagger Annotation
    		@Parameter(description = "Quantity of the product to add", example = "2")
    		
            @PathVariable Integer quantity){
        CartDTO cartDTO = cartService.addProductToCart(productId, quantity);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.CREATED);
    }
    
    // Swagger Annotation
    @Operation(summary = "Get all carts" , description = "Retrieves the list of carts.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Carts retrieved successfully")
	})

    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>> getCarts() {
        List<CartDTO> cartDTOs = cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(cartDTOs, HttpStatus.FOUND);
    }
    
    // Swagger Annotation
    @Operation(summary = "Get current user's cart" , description = "Retrieves the cart associated with the currently logged-in user.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
    	@ApiResponse(responseCode = "401", description = "User is not authenticated")
	})
    
    @GetMapping("/carts/users/cart")
    public ResponseEntity<CartDTO> getCartById(){
        String emailId = authUtil.loggedInEmail();
        Cart cart = cartRepository.findCartByEmail(emailId);
        Long cartId = cart.getCartId();
        CartDTO cartDTO = cartService.getCart(emailId, cartId);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Update cart product quantity" , description = "Increases the quantity by one or decreases it by one. Use 'delete' to decrease the quantity.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Cart updated successfully"),
    	@ApiResponse(responseCode = "404", description = "Cart or product not found")
	})

    @PutMapping("/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProduct(
    		
    		// Swagger Annotation
    		@Parameter(description = "ID of the product to update", example = "1")
    		
    		@PathVariable Long productId,
    		
    		// Swagger Annotation
    		@Parameter(
    			    description = "Operation to perform: use 'delete' to decrease quantity; other values currently increase it",
    			    example = "delete"
    			)
    		
            @PathVariable String operation) {

        CartDTO cartDTO = cartService.updateProductQuantityInCart(productId,
                operation.equalsIgnoreCase("delete") ? -1 : 1);

        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Removes the specified product from a cart using cart ID and product ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Product removed successfully"),
    	@ApiResponse(responseCode = "404", description = "Cart or product not found")
	})
    
    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(
    		// Swagger Annotation
    		@Parameter(description = "ID of the cart", example = "1")
    		
    		@PathVariable Long cartId,
                   
    		// Swagger Annotation
    		@Parameter(description = "ID of the product to remove", example = "2")
    		
    		@PathVariable Long productId) {
        String status = cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
    
    
}

