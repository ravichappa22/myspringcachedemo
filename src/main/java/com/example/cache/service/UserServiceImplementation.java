package com.example.cache.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cache.model.User;
import com.example.cache.repo.UserRepository;

@Service
public class UserServiceImplementation {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> retrieveUsers(){
		Iterator<User> Users = userRepo.findAll().iterator();
		List<User> UserList = new ArrayList<User>();
		while(Users.hasNext()) {
			UserList.add(Users.next());
		}
		return UserList;
	}
	
	
	public User saveUser(User user) {
		//Random r = new Random(1);
		//user.setId(r.nextInt());
		userRepo.save(user);
	    return user;
	}
	
	
	public User retrieveById(long id) {
		return userRepo.getById(id);
	}
	
	public User retrieveByLastName(String lastName) {
		return userRepo.findByLastName(lastName);
	}
	
	public List<User> retrieveUser(String firstName, String lastName) {
		return userRepo.findByUser(firstName, lastName);
	}

}
