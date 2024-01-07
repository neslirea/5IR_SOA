package fr.insa.mas.orchestrationMS;

import java.util.Iterator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrchestrationResource {
	
	RestTemplate restTemplate = new RestTemplate();
	

	/*******************************
	 * 		               		   *
	 * 		User - Mission 		   *
	 * 		               		   *
	 * ****************************/
	private Mission getFullMission(Mission mission, int id_mission) {
		if (mission!=null) {
			Integer idInitiator = (Integer) mission.getInitiator();
			Integer idVolunteer = (Integer) mission.getVolunteer();
			if (idInitiator!=null) {
				mission.setInitiator(restTemplate.getForObject("http://localhost:8088/initiator/"+idInitiator, Initiator.class));
			}
			if (idVolunteer!=null) {
				mission.setVolunteer(restTemplate.getForObject("http://localhost:8088/volunteer/"+idVolunteer, Volunteer.class));
			}
			System.out.println("get mission "+ id_mission+":" + mission.toString());
			System.out.println("initiator : "+ idInitiator);
			System.out.println("volunteer : "+ idVolunteer);
		}
		return mission;
	}
	
	@GetMapping("/mission/{id_mission}")
	public Mission getMission(@PathVariable int id_mission){
		Mission mission = restTemplate.getForObject("http://localhost:8089/mission/"+id_mission, Mission.class);
		return getFullMission(mission, id_mission);
	}
	
	

	@GetMapping("/mission")
	public Mission[] getMission(){
		Mission[] missions = restTemplate.getForObject("http://localhost:8089/mission", Mission[].class);
		for(int i=0; i<missions.length; i++) {
			missions[i] = getFullMission(missions[i], missions[i].getId());
		}
		return missions;
	}
	
	/*******************************
	 * 		               		   *
	 * 	 Review - Mission - User   *
	 * 		               		   *
	 * ****************************/
	private Review[] getFormattedReviews(String url) {
		Review[] reviews = restTemplate.getForObject(url, Review[].class);
		for(int i=0; i<reviews.length; i++) {
			reviews[i].setMission(getMission((int) reviews[i].getMission()));
		}
		return reviews;
	}
	
	@GetMapping("/review")
	public Review[] getReviews(){
		return getFormattedReviews("http://localhost:8090/review");
	}
	
	@GetMapping("/review/{id_volunteer}")
	public Review[] getReviews(@PathVariable int id_volunteer){
		return getFormattedReviews("http://localhost:8089/review/"+id_volunteer);
	}
	
	/*******************************
	 * 		               		   *
	 * 	      User - User          *
	 * 		               		   *
	 * ****************************/
	@GetMapping("/initiator/{idInitiator}")
	public Initiator getInitiator(@PathVariable int idInitiator){
		Initiator initiator = restTemplate.getForObject("http://localhost:8088/initiator/"+idInitiator, Initiator.class);
		Integer validator = (Integer) initiator.getValidator();
		if(validator!=null) {
			initiator.setValidator(restTemplate.getForObject("http://localhost:8088/validator/"+(int)validator, Validator.class));
		}
		return initiator;
	}
	
	@GetMapping("/initiator")
	public Initiator[] getInitiator(){
		Initiator[] initiators = restTemplate.getForObject("http://localhost:8088/initiator", Initiator[].class);
		for(int i=0; i<initiators.length; i++) {		
			Integer validator = (Integer) initiators[i].getValidator();
			if(validator!=null) {
				initiators[i].setValidator(restTemplate.getForObject("http://localhost:8088/validator/"+(int)validator, Validator.class));
			}
		}

		return initiators;
	}
	
}

