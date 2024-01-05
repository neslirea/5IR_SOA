package fr.insa.mas.userManagementMS;

import com.mysql.cj.util.StringUtils;

public abstract class User {

	private int id=-1;
	private String lastName;
	private String firstName;
	
	
	public User(int id, String lastName, String firstName) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	
	public User() {}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public boolean isComplete() {
		return !(StringUtils.isNullOrEmpty(firstName) 
				|| StringUtils.isNullOrEmpty(lastName)
				);
	}
	
	public boolean hasID() {
		return id!=-1;
	}
	
	
	
	
}
