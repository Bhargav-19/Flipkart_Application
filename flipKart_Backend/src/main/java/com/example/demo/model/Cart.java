package com.example.demo.model;

import java.util.List;

import org.apache.catalina.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartid;

	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private Register user;
	    
	    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	    private List<CartItem> cartItems;

		

		public Long getCartid() {
			return cartid;
		}

		public void setCartid(Long cartid) {
			this.cartid = cartid;
		}

		public Register getUser() {
			return user;
		}

		public void setUser(Register user) {
			this.user = user;
		}

		public List<CartItem> getCartItems() {
			return cartItems;
		}

		public void setCartItems(List<CartItem> cartItems) {
			this.cartItems = cartItems;
		}

		@Override
		public String toString() {
			return "Cart [cartid=" + cartid + ", user=" + user + ", cartItems=" + cartItems + "]";
		}

		
	    
	    
}
