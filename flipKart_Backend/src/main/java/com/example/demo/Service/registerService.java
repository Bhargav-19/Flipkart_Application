package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.IregisterRepo;
import com.example.demo.model.Register;

@Service
public class registerService implements IregisterService {

	
	@Autowired
	private  IregisterRepo registerRepo;
	
	public  List<Register> findAll() {	
		return registerRepo.findAll();
	}

	public  Optional<Register> findById(Long id) {
		return registerRepo.findById(id);
	}

	public  Register save(Register register) {
		
		return registerRepo.save(register);
	}

	public  void deleteById(Long id) {
		registerRepo.deleteById(id);
		
	}
	
	 public Register findByName(String userName) {
	        Optional<Register> user = registerRepo.findByuserName(userName);

	        return user.orElse(null);  
	    }

//	public Register findByName(String name) {
//		
//		List<Register> dat=registerRepo.findAll();
//		Register data;
//		String uname;
//		for(long i=0;i<dat.size();i++) {
//			
//			data=registerRepo.findById(i).get();
//			
//			uname=data.getUserName();
//			if(name.equals(uname)) {
//				return data;
//			}
//				
//			
//			
//		}
//		return null;
//	}

}
