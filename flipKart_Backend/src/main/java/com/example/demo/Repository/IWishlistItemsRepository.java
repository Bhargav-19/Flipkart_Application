package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Products;
import com.example.demo.model.Wishlist;
import com.example.demo.model.WishlistItems;


public interface IWishlistItemsRepository  extends JpaRepository<WishlistItems, Long>{

	 List<WishlistItems> findByWishlist(Wishlist wishlist);
	    Optional<WishlistItems> findByWishlistAndProduct(Wishlist wishlist, Products product); 
}
