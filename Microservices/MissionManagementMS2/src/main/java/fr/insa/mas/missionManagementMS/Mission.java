package fr.insa.mas.missionManagementMS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Mission {
	
	private int id=-1;
	private String titre;
	private String description;

	private Date creationDate;
	private Date finishDate;
	
	private int initiator;
	private Integer volunteer;	
	
	private Status status;

	public Mission(int id, String titre, String description, Date creationDate, Date finishDate, int initiator,
			Integer volunteer, Status status) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.creationDate = creationDate;
		this.finishDate = finishDate;
		this.initiator = initiator;
		this.volunteer = volunteer;
		this.status = status;
	}

	public Mission() {
		
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

	public int getInitiator() {
		return initiator;
	}

	public void setInitiator(int initiator) {
		this.initiator = initiator;
	}

	public Integer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Integer volunteer) {
		this.volunteer = volunteer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		
		return "{id : "+ getId() +"}, "
				+ "{titre : "+ getTitre() +"}"
				+ "{description : "+ getDescription() +"}"
				+ "{creationDate : "+ getCreationDate() +"}"
				+ "{finishDate : "+ getFinishDate() +"}"
				+ "{initiator : "+ getInitiator() +"}"
				+ "{volunteer : "+ getVolunteer() +"}"
				+ "{status : "+ getStatus() +"}"
		;
		
	}
}
