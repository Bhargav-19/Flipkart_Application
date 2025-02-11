package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class CartItem {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartItemId;

	    @ManyToOne
	    @JoinColumn(name = "cart_id")
	    @JsonIgnore
	    private Cart cart;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Products product;

	    private int quantity;
	    
	    
	    // ✅ Default Constructor (Required for JPA)
	    public CartItem() {}

	    // ✅ Parameterized Constructor (Fixes the Error)
	    public CartItem(Cart cart, Products product, int quantity) {
	        this.cart = cart;
	        this.product = product;
	        this.quantity = quantity;
	    }
	    

		

		public Long getCartItemId() {
			return cartItemId;
		}

		public void setCartItemId(Long cartItemId) {
			this.cartItemId = cartItemId;
		}

		public Cart getCart() {
			return cart;
		}

		public void setCart(Cart cart) {
			this.cart = cart;
		}

		public Products getProduct() {
			return product;
		}

		public void setProduct(Products product) {
			this.product = product;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return "CartItem [cartItemId=" + cartItemId + ", cart=" + cart + ", product=" + product + ", quantity="
					+ quantity + "]";
		}

		
	    
	    
	    
}
