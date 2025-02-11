package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;
import com.example.demo.model.Register;

public interface ICartRepository  extends JpaRepository<Cart, Long>{
	  Optional<Cart> findByUser(Register user);
}
