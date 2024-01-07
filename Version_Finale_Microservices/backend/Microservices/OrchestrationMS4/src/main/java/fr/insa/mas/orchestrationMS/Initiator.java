package fr.insa.mas.orchestrationMS;

public class Initiator extends User {
	
	private Object validator = null;

	public Initiator(int id, String lastName, String firstName, int validator) {
		super(id, lastName, firstName);
		this.validator = validator;
	}
	
	public Initiator(int id, String lastName, String firstName) {
		super(id, lastName, firstName);
	}

	public Initiator() {}

	public Object getValidator() {
		return validator;
	}

	public void setValidator(Object validator) {
		this.validator = validator;
	}
	
	public void deleteValidator() {
		this.validator = null;
	}

}
