package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.CartItemService;
import com.example.demo.Service.CartService;
import com.example.demo.Service.WishlistItemsService;
import com.example.demo.Service.WishlistService;
import com.example.demo.model.WishlistItems;




@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

	 @Autowired
	    private WishlistService wishlistService;

	    @Autowired
	    private WishlistItemsService wishlistItemsService;
	    
	    
	    @Autowired
	    private CartService cartService;

	    @Autowired
	    private CartItemService cartItemService;

	    // ✅ Add product to cart (from CartService)
	    @PostMapping("/addtoCart")
	    public String addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
	        cartService.addToCart(userId, productId, quantity);
	        return "Product added to cart successfully!";
	    }

	    // ✅ Add product to Wishlist (from WishlistService)
	    @PostMapping("/addtoWishlist")
	    public String addToWishlist(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
	    	wishlistService.addToWishlist(userId, productId, quantity);
	        return "Product added to wishlist successfully!";
	    }

	    // ✅ Get all Wishlist items (from WishlistService)
	    @GetMapping("/items/{userId}")
	    public List<WishlistItems> getWishlistItems(@PathVariable Long userId) {
	        return wishlistService.getWishlistItems(userId);
	    }

	    // ✅ Remove a product from the Wishlist (from WishlistItemService)
	    @DeleteMapping("/remove/{wishlistItemId}")
	    public String removeWishlistItem(@PathVariable Long wishlistItemId) {
	    	wishlistItemsService.removewishlistItem(wishlistItemId);
	        return "wishlist item removed successfully.";
	    }

	    // ✅ Update Wishlist item quantity (from WishlistItemService)
	    @PutMapping("/update/{cartItemId}")
	    public WishlistItems updateWishlistItems(@PathVariable Long wishlistItemId, @RequestParam int quantity) {
	        return wishlistItemsService.updateWishlistItemsQuantity(wishlistItemId, quantity);
	    }

	    // ✅ Clear the entire Wishlist (from WishlistService)
	    @DeleteMapping("/clear/{userId}")
	    public String clearWishlist(@PathVariable Long userId) {
	    	wishlistService.clearWishlist(userId);
	        return "Wishlist cleared!";
	    }
}
