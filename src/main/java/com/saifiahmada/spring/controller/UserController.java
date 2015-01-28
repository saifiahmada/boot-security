package com.saifiahmada.spring.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saifiahmada.spring.domain.RoleEnum;
import com.saifiahmada.spring.domain.User;
import com.saifiahmada.spring.domain.UserRole;
import com.saifiahmada.spring.service.UserService;
import com.saifiahmada.spring.util.PasswordCrypto;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User getUser(){
		return new User();
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(){
		return "user-form";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("users", userService.findAll());
		return "user-list";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user){
		System.out.println("" + user.getUsername());
		System.out.println("" + user.getPassword());
		System.out.println("" + user.getEmail());
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		System.out.println("hash " + hashedPassword);
		/*String encpass = PasswordCrypto.getInstance().encrypt(user.getPassword());
		System.out.println("encpass = " + encpass); */
		user.setPassword(hashedPassword);
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(new UserRole(RoleEnum.USER.toString(), user));
		user.setRoles(roles); 
		
		userService.save(user);
		return "redirect:/user/form.html";
	}

}
