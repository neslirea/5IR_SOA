package fr.insa.mas.reviewManagementMS;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.reviewManagementMS.dao.ReviewDAO;

@RestController
public class ReviewResource {
	
	@GetMapping("/review/{id_volunteer}")
	public Review[] getReviews(@PathVariable int id_volunteer){
		return ReviewDAO.getReviews(id_volunteer);
	}
	
	@GetMapping("/review/mean/{id_volunteer}")
	public int getMean(@PathVariable int id_volunteer){
		return ReviewDAO.getMeanReviews(id_volunteer);
	}
	
	
	@PostMapping(value="/review")
	public void addReview(@RequestBody Review rev) {
		System.out.println("Review : " + rev.toString());
		ReviewDAO.addReview(rev);
	}

	
}

