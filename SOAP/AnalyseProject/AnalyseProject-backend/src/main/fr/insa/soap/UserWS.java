package fr.insa.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.insa.soap.dao.UserDAO;

@WebService(serviceName="userWS")
public class UserWS {
	
	@WebMethod(operationName="createUser")
	public int createUser(@WebParam(name="user") User user) {
		UserDAO.addUser(user);
		return 0;
	}
		
	@WebMethod(operationName="getUsers")
	public User[] getUsers() {
		return UserDAO.getUsers();
	}

	@WebMethod(operationName="setValidator")
	public int setValidator(@WebParam(name="user") int user, @WebParam(name="validator") int validator) {
		return UserDAO.setValidator(user, validator);
	}
}
