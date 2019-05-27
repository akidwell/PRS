package com.prs.web;

import java.time.LocalDateTime;
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
import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.User;
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

	
	//don't think i need this one anymore since i have the submit new method, which is essentially the same thing
//	@PostMapping("/")
//	public JsonResponse addPurchaseRequest(@RequestBody PurchaseRequest pr) {
//		JsonResponse jr = null;
//		// NOTE: May need to enhance exception handling if more than one exception type
//		// needs caught
//		try {
//			jr = JsonResponse.getInstance(prRepo.save(pr));
//		} catch (Exception e) {
//			jr = JsonResponse.getInstance(e);
//		}
//		return jr;
//	}

	@PutMapping("/")
	public JsonResponse updatePurchaseRequest(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			if (prRepo.existsById(pr.getId())) {
				jr = JsonResponse.getInstance(prRepo.save(pr));
			} else {
				jr = JsonResponse.getInstance(
						"Purchase Request id: " + pr.getId() + "doesn't exist and " + "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse deletePurchaseRequest(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			if (prRepo.existsById(pr.getId())) {
				prRepo.delete(pr);
				jr = JsonResponse.getInstance("Purchase Request deleted");
			} else {
				jr = JsonResponse.getInstance(
						"Purchase request id: " + pr.getId() + "doesn't exist and " + "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/submit-new")
	public JsonResponse setStatusToNew(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			pr.setStatus("New");
			pr.setSubmittedDate(LocalDateTime.now());
			jr = JsonResponse.getInstance(prRepo.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/submit-review")
	public JsonResponse submitForReview(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			if (pr.getTotal() <= 50.00) {
				pr.setStatus("Approved");
			} else {
				pr.setStatus("Review");
			}
			pr.setSubmittedDate(LocalDateTime.now());
			jr = JsonResponse.getInstance(prRepo.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/list-review")
	public JsonResponse getPRthatNeedReviewedAndNotReviewer (@RequestBody User u) {
			JsonResponse jr = null;
			try {
				Iterable<PurchaseRequest> pr = prRepo.findByStatusAndUserNot("Review", u);
					jr = JsonResponse.getInstance(pr);
			} catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			} return jr;
}
	
	@PutMapping("/approve")
	public JsonResponse setStatusToApproved(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			pr.setStatus("Approved");
			jr = JsonResponse.getInstance(prRepo.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PutMapping("/reject")
	public JsonResponse setStatusToRejected(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			pr.setStatus("Reject");
			jr = JsonResponse.getInstance(prRepo.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
}
