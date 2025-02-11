package com.example.demo.Service;

import java.util.List;
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

import jakarta.transaction.Transactional;

@Service
public class WishlistService {
	
	 @Autowired
	    private IWishlistRepository WishlistRepository;
	
	 @Autowired
	    private IWishlistItemsRepository WishlistItemsRepository;
	
	 
	 @Autowired
	    private IProductRepository productRepository;

	    @Autowired
	    private IregisterRepo userRepository;
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	    // ✅ **Add product to the cart**
	    public void addToWishlist(Long userId, Long productId, int quantity) {
	        // Find user & product
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
	        
	       
	       

	        // ✅ Optimize database query: Fetch cart item directly
	        Optional<WishlistItems> existingWishlistItems = WishlistItemsRepository.findByWishlistAndProduct(wishlist, product);

	        existingWishlistItems.ifPresentOrElse(
	        		WishlistItems -> {
	        			WishlistItems.setQuantity(WishlistItems.getQuantity() + quantity);
	                    WishlistItemsRepository.save(WishlistItems);
	                },
	                () -> {
	                	WishlistItems newWishlistItems = new WishlistItems(wishlist,product,quantity);
	                	WishlistItemsRepository.save(newWishlistItems);
	                }
	        );
	    }

	    // ✅ **Get all items in the user's cart**
	    
	    @Transactional
	    public List<WishlistItems> getWishlistItems(Long userId) {
	        Register user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        Wishlist wishlist = WishlistRepository.findByUser(user)
	                .orElseThrow(() -> new RuntimeException("wishlist not found"));
	        return WishlistItemsRepository.findByWishlist(wishlist);
	    }

	    // ✅ **Remove an item from the cart**
	    public void removeFromWishlist(Long userId, Long productId) {
	        Register user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        Products product = productRepository.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));
	        Wishlist wishlist = WishlistRepository.findByUser(user)
	                .orElseThrow(() -> new RuntimeException("wishlist not found"));

	        Optional<WishlistItems> wishlistItems = WishlistItemsRepository.findByWishlistAndProduct(wishlist, product);

	        wishlistItems.ifPresent(WishlistItemsRepository::delete);
	    }

	    // ✅ **Clear all items from the user's cart**
	    public void clearWishlist(Long userId) {
	        Register user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        Wishlist wishlist = WishlistRepository.findByUser(user)
	                .orElseThrow(() -> new RuntimeException("wishlist not found"));

	        List<WishlistItems> wishlistItems = WishlistItemsRepository.findByWishlist(wishlist);
	        WishlistItemsRepository.deleteAll(wishlistItems);
	    }
	    
	    
	    

}
