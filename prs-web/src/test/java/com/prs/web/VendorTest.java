package com.prs.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.business.User;
import com.prs.business.Vendor;
import com.prs.db.VendorRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VendorTest {

	@Autowired
	private VendorRepository vendorRepo;

	@Test
	public void testVendorGetAll() {
		Iterable<Vendor> vendors = vendorRepo.findAll();
		assertNotNull(vendors);
	}
	
	//wanting to make sure that if a non-unique vendor code is entered, 
	//an error is thrown and that the vendor is not saved.
	@Test(expected = DataIntegrityViolationException.class)
	public void testAddNotUniqueVendorCode() {
		Vendor v= new Vendor("bc-1001", "name", "address", "city","ky",41071, "num","email", true);
		assertNotNull(vendorRepo.save(v));
		
	}
	
	
}