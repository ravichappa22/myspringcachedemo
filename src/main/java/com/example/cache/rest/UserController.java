package com.example.cache.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.model.User;
import com.example.cache.service.UserServiceImplementation;

@RestController
public class UserController {
	
	@Autowired
	private  UserServiceImplementation userService;
	
	@RequestMapping(value="/users")
	public List<User> retrieveUsers(){
		
		return userService.retrieveUsers();
	}
	
	@RequestMapping(value="/user", produces="application/json", method=RequestMethod.POST)
	public User saveUser(@RequestBody User User){
		
		 return userService.saveUser(User);
	}
	
	@RequestMapping(value="/user/id/{id}",  produces="application/json",method=RequestMethod.GET)
	public User retrieveById(@PathVariable long id) {
		return userService.retrieveById(id);
	}
	
	@RequestMapping(value="/lastname/{name}", produces="application/json",method=RequestMethod.GET)
	public List<User> saveUsers(@PathVariable String name){
		
		 return userService.retrieveByLastName(name);
	}
	
	@RequestMapping(value="/name/{firstName}/{lastName}", produces="application/json",method=RequestMethod.GET)
	public List<User> findUsers(@PathVariable String firstName, @PathVariable String lastName){
		
		 return userService.retrieveUser(firstName, lastName);
	}

}
