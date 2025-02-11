package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

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

import jakarta.transaction.Transactional;

@Service
public class CartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IregisterRepo userRepository;
    
    

    // ✅ **Add product to the cart**
    public void addToCart(Long userId, Long productId, int quantity) {
        // Find user & product
        Register user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get or create cart
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        // ✅ Optimize database query: Fetch cart item directly
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        existingCartItem.ifPresentOrElse(
                cartItem -> {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItemRepository.save(cartItem);
                },
                () -> {
                    CartItem newCartItem = new CartItem(cart,product,quantity);
                    cartItemRepository.save(newCartItem);
                }
        );
    }

    // ✅ **Get all items in the user's cart**
    
    @Transactional
    public List<CartItem> getCartItems(Long userId) {
        Register user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartItemRepository.findByCart(cart);
    }

    // ✅ **Remove an item from the cart**
    public void removeFromCart(Long userId, Long productId) {
        Register user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product);

        cartItem.ifPresent(cartItemRepository::delete);
    }

    // ✅ **Clear all items from the user's cart**
    public void clearCart(Long userId) {
        Register user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        cartItemRepository.deleteAll(cartItems);
    }
}
