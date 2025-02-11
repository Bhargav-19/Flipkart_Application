package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.IProductRepository;
import com.example.demo.Repository.IWishlistItemsRepository;
import com.example.demo.Repository.IWishlistRepository;
import com.example.demo.Repository.IregisterRepo;

import com.example.demo.model.Products;
import com.example.demo.model.Register;
import com.example.demo.model.Wishlist;
import com.example.demo.model.WishlistItems;

@Service
public class WishlistItemsService {

	 @Autowired
	    private IWishlistRepository WishlistRepository;
	
	 @Autowired
	    private IWishlistItemsRepository WishlistItemsRepository;
	
	 
	 @Autowired
	    private IProductRepository productRepository;

	    @Autowired
	    private IregisterRepo userRepository;
	    
	    
	    
	    
	    
	    
	    // ✅ **Add item to Wishlist**
	    public WishlistItems addWishlistItems(Long userId, Long productId, int quantity) {
	        Register user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Products product = productRepository.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        Wishlist wishlist = WishlistRepository.findByUser(user)
	                .orElseGet(() -> {
	                	Wishlist newwishlist = new Wishlist();
	                    newwishlist.setUser(user);
	                    return WishlistRepository.save(newwishlist);
	                });

	        Optional<WishlistItems> existingWishlistItems = WishlistItemsRepository.findByWishlist(wishlist).stream()
	                .filter(wishlistItems -> wishlistItems.getProduct().equals(product))
	                .findFirst();

	        if (existingWishlistItems.isPresent()) {
	        	WishlistItems wishlistItems = existingWishlistItems.get();
	        	wishlistItems.setQuantity(wishlistItems.getQuantity() + quantity);
	            return WishlistItemsRepository.save(wishlistItems);
	        } else {
	        	WishlistItems newWishlistItems = new WishlistItems();
	        	newWishlistItems.setWishlist(wishlist);
	        	newWishlistItems.setProduct(product);
	        	newWishlistItems.setQuantity(quantity);
	            return WishlistItemsRepository.save(newWishlistItems);
	        }
	    }

	    // ✅ **Remove an item from the Wishlist**
	    public void removewishlistItem(Long wishlistItemId) {
	    	WishlistItems wishlistItems = WishlistItemsRepository.findById(wishlistItemId)
	                .orElseThrow(() -> new RuntimeException("WishlistItems Item not found"));
	    	WishlistItemsRepository.delete(wishlistItems);
	    }

	    // ✅ **Update item quantity in the Wishlist**
	    public WishlistItems updateWishlistItemsQuantity(Long wishlistItemId, int newQuantity) {
	    	WishlistItems wishlistItem= WishlistItemsRepository.findById(wishlistItemId)
	                .orElseThrow(() -> new RuntimeException("Wishlist Item not found"));

	        if (newQuantity <= 0) {
	        	WishlistItemsRepository.delete(wishlistItem);
	            return null; // Item removed from Wishlist
	        } else {
	        	wishlistItem.setQuantity(newQuantity);
	            return WishlistItemsRepository.save(wishlistItem);
	        }
	    }

	    
	    
}
