package com.prs;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.User;
import com.prs.db.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepoTests {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepo;
	
	
	@Test
	public void findByUsernameShouldReturnUser() {
		entityManager.persist(new User("name","p","fname","lname","phone","email",true,true));
		Optional<User> user = userRepo.findByUserName("name");
		assertThat(user.get().getFirstName()).isEqualTo("fname");
		
	}
	
}
