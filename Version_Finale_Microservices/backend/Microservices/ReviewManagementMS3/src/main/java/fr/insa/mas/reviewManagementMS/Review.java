package fr.insa.mas.reviewManagementMS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Review {
	
	private int id=-1;
	
	private int note;

	private int mission;
	
	private String description;
	

	public Review() {
		
	}
	

	public Review(int id, int note, int mission, String description) {
		super();
		this.id = id;
		this.note = note;
		this.mission = mission;
		this.description = description;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNote() {
		return note;
	}



	public void setNote(int note) {
		this.note = note;
	}



	public int getMission() {
		return mission;
	}



	public void setMission(int mission) {
		this.mission = mission;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		
		return "{id : "+ getId() +"}, "
				+ "{note : "+ getNote() +"}"
				+ "{mission : "+ getMission() +"}"
				+ "{description : "+ getDescription() +"}"
		;
		
	}
}
