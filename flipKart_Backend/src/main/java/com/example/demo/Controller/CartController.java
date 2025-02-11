package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.CartService;
import com.example.demo.model.CartItem;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    // ✅ Add product to cart (from CartService)
    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(userId, productId, quantity);
        return "Product added to cart successfully!";
    }

    // ✅ Get all cart items (from CartService)
    @GetMapping("/items/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    // ✅ Remove a product from the cart (from CartItemService)
    @DeleteMapping("/remove/{cartItemId}")
    public String removeCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return "Cart item removed successfully.";
    }

    // ✅ Update cart item quantity (from CartItemService)
    @PutMapping("/update/{cartItemId}")
    public CartItem updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        return cartItemService.updateCartItemQuantity(cartItemId, quantity);
    }

    // ✅ Clear the entire cart (from CartService)
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared!";
    }
}
