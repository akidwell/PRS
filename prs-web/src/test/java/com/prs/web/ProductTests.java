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
import com.prs.business.Vendor;
import com.prs.db.ProductRepository;
import com.prs.db.VendorRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTests {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private VendorRepository vendorRepo;
	

	@Test
	public void testUserGetAll() {
		Iterable<Product> products = productRepo.findAll();
		assertNotNull(products);

	}

	@Test
	public void testProductAddAndDelete() {
		// create new user
		//Optional<Vendor> v = vendorRepo.findById(1);
		Iterable<Vendor> vendors = vendorRepo.findAll();
		Vendor v = vendors.iterator().next();
		Product p = new Product(v, "pn","name", 10.99, " ", " ");
		// save new user
		assertNotNull(productRepo.save(p));
		// assert that last name is correct
		assertEquals("name", p.getName());
		// delete user
		productRepo.delete(p);
		// confirm deletion
		assertNotNull(productRepo.findById(p.getId()));
	}
}
