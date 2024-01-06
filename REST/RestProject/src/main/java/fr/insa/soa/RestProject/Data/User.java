package fr.insa.soa.RestProject.Data;

import java.util.ArrayList;

import fr.insa.soa.RestProject.Ressources.Link;

public class User {

	private int id;
	private String firstName;	
	private String lastName;
	private int validator;
	private ArrayList<Link> links = new ArrayList<>();
	
	public User() {
		this.validator = -1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getValidator() {
		return validator;
	}

	public void setValidator(int validator) {
		this.validator = validator;
	}
	
	public boolean hasValidator() {
		return this.validator!=-1;
	}

	public void addLink(String uri, String rel, String methode) {
		Link newLink = new Link();
		newLink.setUri(uri);
		newLink.setRel(rel);
		newLink.setMethode(methode);
		links.add(newLink);
	}
	
	public ArrayList<Link> getLinks(){
		return links;
	}

	
}
