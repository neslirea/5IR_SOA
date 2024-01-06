package fr.insa.mas.orchestrationMS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Mission {
	
	private int id=-1;
	private String titre;
	private String description;

	private Date creationDate;
	private Date finishDate;
	
	private Object initiator;
	private Object volunteer;		

	private Status status;

	public Mission() {
		
	}
	
	public Mission(Mission mission) {
		this.setId(mission.getId());
		this.setTitre(mission.getTitre());
		this.setDescription(mission.getDescription());
		this.setCreationDate(mission.getCreationDate());
		this.setFinishDate(mission.getFinishDate());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public Object getInitiator() {
		return initiator;
	}

	public void setInitiator(Object initiator) {
		this.initiator = initiator;
	}

	public Object getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Object volunteer) {
		this.volunteer = volunteer;
	}

	@Override
	public String toString() {
		
		return "{id : "+ getId() +"}, "
				+ "{titre : "+ getTitre() +"}"
				+ "{description : "+ getDescription() +"}"
				+ "{creationDate : "+ getCreationDate() +"}"
				+ "{finishDate : "+ getFinishDate() +"}"
				+ "{status : "+ getStatus() +"}"
		;
		
	}
}
