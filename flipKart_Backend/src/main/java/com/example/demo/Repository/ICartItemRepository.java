package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Products;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
	  List<CartItem> findByCart(Cart cart);
	    Optional<CartItem> findByCartAndProduct(Cart cart, Products product); 
}
