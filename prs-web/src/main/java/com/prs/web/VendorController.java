package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.JsonResponse;
import com.prs.business.Vendor;
import com.prs.db.VendorRepository;

@CrossOrigin
@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(vendorRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if (v.isPresent())
			jr = JsonResponse.getInstance(v);
			else 
				jr = JsonResponse.getInstance("No vendor found for id: " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addVendor(@RequestBody Vendor v) {
		JsonResponse jr = null;
//NOTE: May need to enhance exception handling if more than one exception type needs caught
		try {
			jr = JsonResponse.getInstance(vendorRepo.save(v));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	
	@PutMapping("/")
	public JsonResponse updateVendor(@RequestBody Vendor v) {
		JsonResponse jr = null;
//NOTE: May need to enhance exception handling if more than one exception type needs caught
		try {
			if (vendorRepo.existsById(v.getId())) {
			jr = JsonResponse.getInstance(vendorRepo.save(v));
			} else {
				jr = JsonResponse.getInstance("Vendor id: " + v.getId() + "doesn't exist and "
						+ "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("/")
	public JsonResponse deleteVendor(@RequestBody Vendor v) {
		JsonResponse jr = null;
//NOTE: May need to enhance exception handling if more than one exception type needs caught
		try {
			if (vendorRepo.existsById(v.getId())) {
				vendorRepo.delete(v);
			jr = JsonResponse.getInstance("Vendor deleted");
			} else {
				jr = JsonResponse.getInstance("Vendor id: " + v.getId() + "doesn't exist and "
						+ "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
}