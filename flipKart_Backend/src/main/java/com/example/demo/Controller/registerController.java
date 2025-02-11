package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.registerService;
import com.example.demo.model.Register;

@RestController

@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200")
public class registerController {

	
	@Autowired
	private registerService registerServ;
	
	 @GetMapping
	    public List<Register> getUser() {
	        return registerServ.findAll();
	    }
	 @GetMapping("/id/{id}")
	    public Optional<Register> getUserById(@PathVariable Long id) {
	        return registerServ.findById(id);
	    }
	 
	 @GetMapping("/name/{name}")
	    public Register getUserByName(@PathVariable String name) {
		 System.out.println(name);
	        return registerServ.findByName(name);
	    }
	 
	 @PostMapping
	    public Register createUser(@RequestBody Register register) {
		 System.out.println("Received: " + register);

	        return registerServ.save(register);
	    }

	    @PutMapping("/{id}")
	    public Register updateUser(@PathVariable Long id, @RequestBody Register register) {
	    	register.setId(id);
	        return registerServ.save(register);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteUser(@PathVariable Long id) {
	    	registerServ.deleteById(id);
	    }
	
	
	
}
