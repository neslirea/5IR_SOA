package fr.insa.mas.userManagementMS;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.userManagementMS.dao.UserDAO;

@RestController
public class UserResource {
	
	@GetMapping("user/initiator/{id}")
	public User getInitiator(@PathVariable int id){
		return UserDAO.getInitiator(id);
	}
	
	@GetMapping("user/validator/{id}")
	public User getValidator(@PathVariable int id){
		return UserDAO.getValidator(id);
	}
	
	@GetMapping("user/volunteer/{id}")
	public User getVolunteer(@PathVariable int id){
		return UserDAO.getVolunteer(id);
	}
	
	@PutMapping("user/initiator/{id}/{id2}")
	public void updateInitiator(@PathVariable int id, @PathVariable int id2) {
		System.out.println("Change validator : " + id + " ->" + id2);
		UserDAO.addValidator(id, id2);
	}
	
	@PostMapping(value="user/addVolunteer")
	public void addVolunteer(@RequestBody Volunteer vol) {
		System.out.println("Volunteer : " + vol.getId() + vol.getFirstName() + vol.getLastName());
		UserDAO.addVolunteer(vol);
	}
	
	@PostMapping(value="user/addInitiator")
	public void addInitiator(@RequestBody Initiator ini) {
		System.out.println("Initiator : " + ini.getId() + ini.getFirstName() + ini.getLastName());
		UserDAO.addInitiator(ini);
	}
	
	@PostMapping(value="user/addValidator")
	public void addValidator(@RequestBody Validator val) {
		System.out.println("Validator : " + val.getId() + val.getFirstName() + val.getLastName());
		UserDAO.addValidator(val);
	}
}

