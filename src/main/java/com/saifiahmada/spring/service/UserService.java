package com.saifiahmada.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saifiahmada.spring.domain.User;
import com.saifiahmada.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user){
		userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

}
