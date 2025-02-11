package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Wishlist {
	
	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long wishlistid;

	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private Register user;
	    
	    @OneToMany(mappedBy = "wishlist", fetch = FetchType.EAGER)
	    private List<WishlistItems> WishlistItems;

		public Long getWishlistid() {
			return wishlistid;
		}

		public void setWishlistid(Long wishlistid) {
			this.wishlistid = wishlistid;
		}

		public Register getUser() {
			return user;
		}

		public void setUser(Register user) {
			this.user = user;
		}

		public List<WishlistItems> getWishlistItems() {
			return WishlistItems;
		}

		public void setWishlistItems(List<WishlistItems> wishlistItems) {
			WishlistItems = wishlistItems;
		}

		@Override
		public String toString() {
			return "Wishlist [wishlistid=" + wishlistid + ", user=" + user + ", WishlistItems=" + WishlistItems + "]";
		}

	    
	    

}
