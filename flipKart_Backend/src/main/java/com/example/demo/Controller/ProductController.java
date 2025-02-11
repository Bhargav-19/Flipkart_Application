package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.ProductsService;
import com.example.demo.model.Products;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/products")
public class ProductController {

	
	 @Autowired
	    private ProductsService productService;
	 
	 
	 
	 
	 
	 @PostMapping
	    public ResponseEntity<String> saveProduct(
	            @RequestParam String productName,
	            @RequestParam String description,
	            @RequestParam double price,
	            @RequestParam String category,
	            @RequestParam MultipartFile image) {
	        try {
	            productService.saveProduct(productName, description, price, category, image);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully!");
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save product: " + e.getMessage());
	        }
	    }
	 
	 
	 
	 // Get a product by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id) {
	        Products product = productService.getProductById(id);
	        if (product != null) {
	            return new ResponseEntity<>(product, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	 // GET all products
	    @GetMapping
	    public ResponseEntity<List<Products>> getAllProducts() {
	        List<Products> products = productService.getAllProducts();  // Service to fetch all products
	        if (products != null && !products.isEmpty()) {
	            return new ResponseEntity<>(products, HttpStatus.OK);  // Return products if found
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if no products are found
	        }
	    }
	 
	    
	 
	 
	 

//	    @GetMapping("/{id}/image")
//	    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
//	        Products product = productService.getProductById(id);
//	        if (product != null && product.getImage() != null) {
//	            return ResponseEntity.ok()
//	                    .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
//	                    .body(product.getImage());
//	        }
//	        return ResponseEntity.notFound().build();
//	    }
	}




	 
//	 @PostMapping
//	    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
//	        Products savedProduct = productService.saveProduct(product);
//	        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//	    }
	 
//	 @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	    public ResponseEntity<?> addProduct(
//	        @RequestParam("productName") String productName,
//	        @RequestParam("description") String description,
//	        @RequestParam("price") Double price,
//	        @RequestParam("category") String category,
//	        @RequestPart(value = "image", required = false) MultipartFile image
//    ) throws IOException {
//		 
//		 System.out.println("Product Name: " + productName);
//	        System.out.println("Description: " + description);
//	        System.out.println("Price: " + price);
//	        System.out.println("Category: " + category);
//	        System.out.println("Image: " + (image != null ? image.getOriginalFilename() : "No image provided"));
//	        
//	        Products product = new Products();
//	        product.setProductName(productName);
//	        product.setDescription(description);
//	        product.setPrice(price);
//	        product.setCategory(category);
//
//	        if (image != null) {
//	            // Handle image saving logic (store file locally or save URL to DB)
//	            String imageUrl = saveImage(image); // Implement saveImage method
//	            product.setImageUrl(imageUrl);
//	        }
//
//	        // Call service to save the product to the database
//	        productService.saveProduct(product);
//
//	        return ResponseEntity.ok("Product added successfully");
//	    }
//	 private String saveImage(MultipartFile image) throws IOException {
//	        // Implement image saving logic, like saving the file to a folder
//	        // and returning the URL or path
//	        return (image != null ? image.getOriginalFilename() : "No image provided");
//	    }
//
//	        
	        
	        
	        
	 
//	// Get all products
//	    @GetMapping
//	    public ResponseEntity<List<Products>> getAllProducts() {
//	        List<Products> products = productService.getAllProducts();
//	        return new ResponseEntity<>(products, HttpStatus.OK);
//	    }
//	    
	    // Get a product by ID
//	    @GetMapping("/{id}")
//	    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id) {
//	        Optional<Products> product = Optional.ofNullable(productService.getProductById(id));
//	        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//	                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	    }
//	    
//	    
	    
//	 // Update a product
//	    @PutMapping("/{id}")
//	    public ResponseEntity<Products> updateProduct(@PathVariable("id") Long id, @RequestBody Products product) {
//	        Products updatedProduct = productService.updateProduct(id, product);
//	        if (updatedProduct != null) {
//	            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	    }
//	    // Delete a product
//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
//	        productService.deleteProduct(id);
//	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    }
//}
