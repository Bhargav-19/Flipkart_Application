package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WishlistItems {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long WishlistItemsId;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    @JsonIgnore
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    private int quantity;

    
    
    public WishlistItems() {}
    
    
    
	public WishlistItems( Wishlist wishlist, Products product, int quantity) {
		super();
		this.wishlist = wishlist;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getWishlistItemsId() {
		return WishlistItemsId;
	}

	public void setWishlistItemsId(Long wishlistItemsId) {
		WishlistItemsId = wishlistItemsId;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
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
		return "WishlistItems [WishlistItemsId=" + WishlistItemsId + ", wishlist=" + wishlist + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
    
    
    
    
}
