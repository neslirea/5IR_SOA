package fr.insa.mas.userManagementMS;

public class Initiator extends User {
	
	private int validator = -1;

	public Initiator(int id, String lastName, String firstName, int validator) {
		super(id, lastName, firstName);
		this.validator = validator;
	}
	
	public Initiator(int id, String lastName, String firstName) {
		super(id, lastName, firstName);
	}

	public Initiator() {}

	public int getValidator() {
		return validator;
	}

	public void setValidator(int validator) {
		this.validator = validator;
	}
	
	public boolean hasValidator() {
		return this.validator != -1;
	}
	
	public void deleteValidator() {
		this.validator = -1;
	}

}
