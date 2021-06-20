package com.cg.hims.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.User;
import com.cg.hims.service.IUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class IUserController {

	@Autowired
	private IUserService  userservice;


	@GetMapping(value= "/user/{id}")
	public Optional<User> getUser(@PathVariable int id)  {

		return userservice.findUserById(id);       
	}

	@GetMapping(value= "/getUsers")
	public List<User>getUsers()  {

		return userservice.getUsers();       
	}

	@PostMapping(value= "/adduser")
	public User addUser(@Valid @RequestBody User user) {       
		return userservice.addUser(user);
	}

	@PutMapping(value= "/user/update/{id}")
	public User updateUser(@Valid @RequestBody User upduser, @PathVariable int id) {
		return userservice.updateUser(upduser);
	}


}
