package com.ecommerce.project.service;

import java.awt.image.ImagingOpException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;

public interface ProductService {

	ProductDTO addProduct(Long categoryId, ProductDTO productDTO);

	//ProductResponse getAllProducts();

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductDTO updateProduct(Long productId, ProductDTO productDTO);

	ProductDTO deleteProduct(Long productId);

	ProductDTO updateProductImage(Long productId, MultipartFile image) throws ImagingOpException, IOException;

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize,  String sortBy, String sortOrder);
	
}
