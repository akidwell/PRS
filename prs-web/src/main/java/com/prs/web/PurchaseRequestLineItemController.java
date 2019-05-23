package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.JsonResponse;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.db.PurchaseRequestLineItemRepository;


	@RestController
	@RequestMapping("/purchaserequestlineitem")
	public class PurchaseRequestLineItemController {

		@Autowired
		private PurchaseRequestLineItemRepository prliRepo;

		@GetMapping("/")
		public JsonResponse getAll() {
			JsonResponse jr = null;
			try {
				jr = JsonResponse.getInstance(prliRepo.findAll());
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@GetMapping("/{id}")
		public JsonResponse get(@PathVariable int id) {
			JsonResponse jr = null;
			try {
				Optional<PurchaseRequestLineItem> prli = prliRepo.findById(id);
				if (prli.isPresent())
				jr = JsonResponse.getInstance(prli);
				else 
					jr = JsonResponse.getInstance("No purchase request line item found for id: " + id);
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@PostMapping("/")
		public JsonResponse addPurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
			JsonResponse jr = null;
	//NOTE: May need to enhance exception handling if more than one exception type needs caught
			try {
				jr = JsonResponse.getInstance(prliRepo.save(prli));
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		
		@PutMapping("/")
		public JsonResponse updatePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
			JsonResponse jr = null;
	//NOTE: May need to enhance exception handling if more than one exception type needs caught
			try {
				if (prliRepo.existsById(prli.getId())) {
				jr = JsonResponse.getInstance(prliRepo.save(prli));
				} else {
					jr = JsonResponse.getInstance("Purchase Request line item id: " + prli.getId() + "doesn't exist and "
							+ "you are attempting to save it");
				}
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@DeleteMapping("/")
		public JsonResponse deletePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
			JsonResponse jr = null;
	//NOTE: May need to enhance exception handling if more than one exception type needs caught
			try {
				if (prliRepo.existsById(prli.getId())) {
					prliRepo.delete(prli);
				jr = JsonResponse.getInstance("Purchase Request line item deleted");
				} else {
					jr = JsonResponse.getInstance("Purchase request line item id: " + prli.getId() + "doesn't exist and "
							+ "you are attempting to save it");
				}
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
	}


