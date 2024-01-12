package fr.insa.mas.userManagementMS;

public class Initiator extends User {
	
	private Integer validator = null;

	public Initiator(int id, String lastName, String firstName, Integer validator) {
		super(id, lastName, firstName);
		this.validator = validator;
	}
	
	public Initiator(int id, String lastName, String firstName) {
		super(id, lastName, firstName);
	}

	public Initiator() {}

	public Integer getValidator() {
		return validator;
	}

	public void setValidator(Integer validator) {
		this.validator = validator;
	}
	
	public boolean hasValidator() {
		return this.validator != null;
	}
	
	public void deleteValidator() {
		this.validator = null;
	}

}
