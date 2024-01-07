package fr.insa.mas.orchestrationMS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Review {
	
	private int id=-1;
	
	private int note;

	private Object mission;
	
	private String description;
	

	public Review() {
		
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



	public Object getMission() {
		return mission;
	}



	public void setMission(Object mission) {
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
