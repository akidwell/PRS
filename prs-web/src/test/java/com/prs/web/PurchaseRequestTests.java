package com.prs.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.business.PurchaseRequest;
import com.prs.business.User;

import com.prs.db.PurchaseRequestRepository;
import com.prs.db.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseRequestTests {
	
	@Autowired
	private PurchaseRequestRepository prRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	@Test
	public void testPurchaseRequestGetAll() {
		Iterable<PurchaseRequest> pr = prRepo.findAll();
		assertNotNull(pr);

	}

	@Test
	public void testPurchaseRequestAddAndDelete() {
		// create new user
		Iterable<User> users = userRepo.findAll();
		User u = users.iterator().next();
		PurchaseRequest pr = new PurchaseRequest(u,"pn","desc", LocalDate.of(2019,06, 01), "pickup", "new", 5.99, LocalDateTime.now(),"zzz");
		// save new pr
		assertNotNull(prRepo.save(pr));
		// assert that is correct
		assertEquals("pn", pr.getJustification());
		// delete p
		prRepo.delete(pr);
		// confirm deletion
		assertNotNull(prRepo.findById(pr.getId()));
	}
	
}
