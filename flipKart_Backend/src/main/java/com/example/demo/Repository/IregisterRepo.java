package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Register;

@Repository
public interface IregisterRepo extends JpaRepository<Register, Long>{

	Optional<Register> findByuserName(String userName);

	
}
