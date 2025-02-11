package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Register;
import com.example.demo.model.Wishlist;


public interface IWishlistRepository extends JpaRepository<Wishlist, Long> {
	 Optional<Wishlist> findByUser(Register user);
}
