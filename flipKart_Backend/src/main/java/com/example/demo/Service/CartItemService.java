package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.ICartItemRepository;
import com.example.demo.Repository.ICartRepository;
import com.example.demo.Repository.IProductRepository;
import com.example.demo.Repository.IregisterRepo;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Products;
import com.example.demo.model.Register;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IregisterRepo userRepository;

    // ✅ **Add item to cart**
    public CartItem addCartItem(Long userId, Long productId, int quantity) {
        Register user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingCartItem = cartItemRepository.findByCart(cart).stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            return cartItemRepository.save(newCartItem);
        }
    }

    // ✅ **Remove an item from the cart**
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item not found"));
        cartItemRepository.delete(cartItem);
    }

    // ✅ **Update item quantity in the cart**
    public CartItem updateCartItemQuantity(Long cartItemId, int newQuantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item not found"));

        if (newQuantity <= 0) {
            cartItemRepository.delete(cartItem);
            return null; // Item removed from cart
        } else {
            cartItem.setQuantity(newQuantity);
            return cartItemRepository.save(cartItem);
        }
    }

    
   
}
