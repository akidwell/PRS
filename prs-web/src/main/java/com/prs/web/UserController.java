package com.prs.web;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.JsonResponse;
import com.prs.business.User;
import com.prs.db.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(userRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<User> u = userRepo.findById(id);
			if (u.isPresent())
			jr = JsonResponse.getInstance(u);
			else 
				jr = JsonResponse.getInstance("No user found for id: " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addUser(@RequestBody User u) {
		JsonResponse jr = null;
//NOTE: May need to enhance exception handling if more than one exception type needs caught
		try {
			jr = JsonResponse.getInstance(userRepo.save(u));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	
	@PutMapping("/")
	public JsonResponse updateUser(@RequestBody User u) {
		JsonResponse jr = null;
//NOTE: May need to enhance exception handling if more than one exception type needs caught
		try {
			if (userRepo.existsById(u.getId())) {
			jr = JsonResponse.getInstance(userRepo.save(u));
			} else {
				jr = JsonResponse.getInstance("User id: " + u.getId() + "doesn't exist and "
						+ "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("/")
	public JsonResponse deleteUser(@RequestBody User u) {
		JsonResponse jr = null;
//NOTE: May need to enhance exception handling if more than one exception type needs caught
		try {
			if (userRepo.existsById(u.getId())) {
				userRepo.delete(u);
			jr = JsonResponse.getInstance("User deleted");
			} else {
				jr = JsonResponse.getInstance("User id: " + u.getId() + "doesn't exist and "
						+ "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	//find user by username and password
		@PostMapping("/authenticate")
		public JsonResponse getAuthenticate(@RequestBody User u) {
			JsonResponse jr = null;
			try {
				Optional<User> user = userRepo.findByUserNameAndPassword(u.getUserName(), u.getPassword());
				if (user.isPresent())
				jr = JsonResponse.getInstance(user);
				else 
					jr = JsonResponse.getInstance("No user found");
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
	
}