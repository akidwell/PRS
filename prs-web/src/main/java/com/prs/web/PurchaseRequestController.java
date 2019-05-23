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
import com.prs.business.PurchaseRequest;
import com.prs.db.PurchaseRequestRepository;


	@RestController
	@RequestMapping("/purchaserequest")
	public class PurchaseRequestController {

		@Autowired
		private PurchaseRequestRepository prRepo;

		@GetMapping("/")
		public JsonResponse getAll() {
			JsonResponse jr = null;
			try {
				jr = JsonResponse.getInstance(prRepo.findAll());
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@GetMapping("/{id}")
		public JsonResponse get(@PathVariable int id) {
			JsonResponse jr = null;
			try {
				Optional<PurchaseRequest> pr = prRepo.findById(id);
				if (pr.isPresent())
				jr = JsonResponse.getInstance(pr);
				else 
					jr = JsonResponse.getInstance("No purchase request found for id: " + id);
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@PostMapping("/")
		public JsonResponse addPurchaseRequest(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
	//NOTE: May need to enhance exception handling if more than one exception type needs caught
			try {
				jr = JsonResponse.getInstance(prRepo.save(pr));
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		
		@PutMapping("/")
		public JsonResponse updatePurchaseRequest(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
	//NOTE: May need to enhance exception handling if more than one exception type needs caught
			try {
				if (prRepo.existsById(pr.getId())) {
				jr = JsonResponse.getInstance(prRepo.save(pr));
				} else {
					jr = JsonResponse.getInstance("Purchase Request id: " + pr.getId() + "doesn't exist and "
							+ "you are attempting to save it");
				}
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@DeleteMapping("/")
		public JsonResponse deletePurchaseRequest(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
	//NOTE: May need to enhance exception handling if more than one exception type needs caught
			try {
				if (prRepo.existsById(pr.getId())) {
					prRepo.delete(pr);
				jr = JsonResponse.getInstance("Purchase Request deleted");
				} else {
					jr = JsonResponse.getInstance("Purchase request id: " + pr.getId() + "doesn't exist and "
							+ "you are attempting to save it");
				}
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
	}

