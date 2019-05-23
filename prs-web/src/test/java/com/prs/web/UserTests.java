package com.prs.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.business.User;
import com.prs.db.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTests {

	@Autowired
	private UserRepository userRepo;
	@Test
	public void testUserGetAll() {
		Iterable<User> users = userRepo.findAll();
		assertNotNull(users);
	}
	@Test
	public void testUserAddAndDelete() {
		//create new user
		User u1= new User("name", "pwd", "fname", "lname", "number", "email", true, true);
		//save new user
		assertNotNull(userRepo.save(u1));
		//assert that last name is correct
		assertEquals("lname", u1.getLastName());
		//delete user
		userRepo.delete(u1);
		//confirm deletion
		assertNotNull(userRepo.findById(u1.getId()));
	}
}
