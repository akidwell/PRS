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
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.Vendor;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;
@CrossOrigin
@RestController
@RequestMapping("/purchaserequestlineitem")
public class PurchaseRequestLineItemController {

	@Autowired
	private PurchaseRequestLineItemRepository prliRepo;
	@Autowired
	private PurchaseRequestRepository prRepo;

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
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			jr = JsonResponse.getInstance(prliRepo.save(prli));
			updateTotal(prli);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse updatePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs caught
		try {
			if (prliRepo.existsById(prli.getId())) {
				jr = JsonResponse.getInstance(prliRepo.save(prli));
				updateTotal(prli);
			} else {
				jr = JsonResponse.getInstance("Purchase Request line item id: " + prli.getId() + "doesn't exist and "
						+ "you are attempting to save it");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequestLineItem> prli = prliRepo.findById(id);
			if (prli.isPresent()) {
				prliRepo.deleteById(id);
				jr = JsonResponse.getInstance(prli);
				updateTotal(prli.orElse(null));
			} else
				jr = JsonResponse.getInstance("Delete failed. No user for id: " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	// update total method that passes in a purchase request lineitem
	// when i call this method and pass in a prli, it will know to do this method on
	// it
	private void updateTotal(PurchaseRequestLineItem prli) {
		// setting sum to zero here to initialize it
		double sum = 0;
		// get all prli's for the given pr
		Iterable<PurchaseRequestLineItem> prlis = prliRepo.findByPurchaseRequest(prli.getPurchaseRequest());
		// loop through all prli's and sum the line item totals into a variable named sum
		for (PurchaseRequestLineItem p : prlis) {
			// doing the logic for each iteration:
			// (updated) sum = sum (from previous li's) + quantity of product from that li x
			// price of product from that li
			sum += p.getQuantity() * p.getProduct().getPrice();
// end of loop logic
		}
		// outside of loop, but still in method
		// have prli get the purchase request and set the purchase request total to
		// whatever the sum is
		prli.getPurchaseRequest().setTotal(sum);
		//now i'm saving it to DB
		prRepo.save(prli.getPurchaseRequest());
	}
	
	
	//Not sure if this is right?
	@GetMapping("/lines-for-pr/{id}")
	public JsonResponse getLinesForPr(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequest> pr = prRepo.findById(id);
			if (pr.isPresent())
				jr = JsonResponse.getInstance(prliRepo.findByPurchaseRequest(pr.orElse(null)));
			else
				jr = JsonResponse.getInstance("No purchase request line items found for id: " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
}
