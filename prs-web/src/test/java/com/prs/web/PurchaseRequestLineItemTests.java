package com.prs.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.business.Product;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;

import com.prs.db.ProductRepository;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseRequestLineItemTests {
	
	@Autowired
	private PurchaseRequestLineItemRepository prliRepo;
	@Autowired
	private PurchaseRequestRepository prRepo;
	@Autowired
	private ProductRepository productRepo;
	
	@Test
	public void testPurchaseRequestLineItemGetAll() {
		Iterable<PurchaseRequestLineItem> prli = prliRepo.findAll();
		assertNotNull(prli);

	}
	
	@Test
	public void testPurchaseRequestLineItemAddAndDelete() {
		// create new product and pr
		Iterable<Product> products = productRepo.findAll();
		Product p = products.iterator().next();
		Iterable<PurchaseRequest> pr = prRepo.findAll();
		PurchaseRequest pr1 = pr.iterator().next();
		PurchaseRequestLineItem prli = new PurchaseRequestLineItem(pr1, p, 5);
		// save new prli
		assertNotNull(prliRepo.save(prli));
		// assert that is correct
		assertEquals(5, prli.getQuantity());
		// delete prli
		prliRepo.delete(prli);
		// confirm deletion
		assertNotNull(prRepo.findById(prli.getId()));
	}
	
	
}
