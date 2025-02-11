package com.example.demo.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.IProductRepository;
import com.example.demo.model.Products;

@Service
public class ProductsService {

	@Autowired
    private IProductRepository productRepository;

    // Save a product
//    public Products saveProduct(Products product) {
//        return productRepository.save(product);
//    }
//
//    // Get all products
//    public List<Products> getAllProducts() {
//        return productRepository.findAll();
//    }
	
	

    public Products saveProduct(String productName, String description, double price, String category, MultipartFile image) throws IOException {
        Products product = new Products();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setImage(image.getBytes()); // Save image as byte[]
        return productRepository.save(product);
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public List<Products> getAllProducts() {
        // Logic to fetch all products from the database
        return productRepository.findAll();  // Assuming you are using JpaRepository
    }
    
   
    // Get product by ID
//    public Products getProductById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }

    public Products updateProduct(Long id, Products product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
//    // Update a product
//    public Products updateProduct(Long id, Products product) {
//    	product.setId(id);
//        return productRepository.save(product);
//    }
}
