package fr.insa.mas.missionManagementMS;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.missionManagementMS.dao.MissionDAO;

@RestController
public class MissionResource {
	
	@GetMapping("/mission/{id_mission}")
	public Mission getMission(@PathVariable int id_mission){
		return MissionDAO.getMission(id_mission);
	}
	
	@GetMapping("/mission")
	public Mission[] getMission(){
		return MissionDAO.getMission();
	}
	
	@PostMapping(value="/mission")
	public void addMission(@RequestBody Mission miss) {
		System.out.println("Mission : " + miss.toString());
		MissionDAO.addMission(miss);
	}
	
	@PutMapping("/mission/{id_mission}/validate")
	public void validate(@PathVariable int id_mission) {
		System.out.println("Validate mission : " + id_mission);
		MissionDAO.updateStatus(id_mission, Status.WaitingForVolunteer);
	}
	
	@PutMapping("/mission/{id_mission}/reject")
	public void reject(@PathVariable int id_mission) {
		System.out.println("Reject mission : " + id_mission);
		MissionDAO.finishMission(id_mission, Status.Rejected);
	}
	
	@PutMapping("/mission/{id_mission}/finish")
	public void finish(@PathVariable int id_mission) {
		System.out.println("Finish mission : " + id_mission);
		MissionDAO.finishMission(id_mission, Status.Finished);
	}
	
	@PutMapping("/mission/{id_mission}/assign/{id_volunteer}")
	public void assign(@PathVariable int id_mission, @PathVariable int id_volunteer) {
		System.out.println("Finish mission : " + id_mission);
		MissionDAO.assignMission(id_mission, id_volunteer, Status.InProgress);
	}
}

