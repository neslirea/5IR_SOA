package fr.insa.mas.userManagementMS.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.insa.mas.userManagementMS.Initiator;
import fr.insa.mas.userManagementMS.User;
import fr.insa.mas.userManagementMS.Validator;
import fr.insa.mas.userManagementMS.Volunteer;

import java.util.ArrayList;

public class UserDAO {
	
	private static Connection getConnect() throws SQLException {
		String connectionUrl = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_050?serverTimezone=UTC";
		return DriverManager.getConnection(connectionUrl, "projet_gei_050", "eiVeiz2r");
	}
	
	private static ResultSet executeQuery(String sqlRequest) throws SQLException {
		System.out.println(sqlRequest);
		Connection conn = getConnect();
	    PreparedStatement ps = conn.prepareStatement(sqlRequest); 
	    return ps.executeQuery();
	}
	
	private static int executeUpdate(String sqlRequest) throws SQLException {
		System.out.println(sqlRequest);
		Connection conn = getConnect();
	    PreparedStatement ps = conn.prepareStatement(sqlRequest); 
	    return ps.executeUpdate();
	}
	
	private static int getNextUserId() {
		String sqlSelectAllPersons = "SELECT MAX(Id) as Nb FROM User;";
		try (ResultSet rs = executeQuery(sqlSelectAllPersons)){
			rs.next();
			int nb = rs.getInt("Nb");
			return nb+1;
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
			return -1;
		}
	}
	
	public static User[] getInitiator() { 
		String sqlSelectAllPersons = "SELECT * FROM User NATURAL JOIN Initiator;";
		ArrayList<User> users = new ArrayList<>();
		try (ResultSet rs = executeQuery(sqlSelectAllPersons)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String name = rs.getString("FIRST_NAME");
		        String lastName = rs.getString("LAST_NAME");
		        int validator = rs.getInt("VALIDATOR");
		        
		        if(validator==0) {validator=-1;}
		        
		        users.add(new Initiator(_id, name, lastName, validator));		            
		    }
	        return users.toArray(User[]::new);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
	}

	private static User[] getUser(String type) {
		String sqlSelectAllPersons = "SELECT * FROM User NATURAL JOIN " + type + ";";
		ArrayList<User> users = new ArrayList<>();
		try (ResultSet rs = executeQuery(sqlSelectAllPersons)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String name = rs.getString("FIRST_NAME");
		        String lastName = rs.getString("LAST_NAME");
		        
		        if ("Validator".equals(type)) {
			        users.add(new Validator(_id, name, lastName));	
		        } else {
			        users.add(new Volunteer(_id, name, lastName));	
		        }	            
		    }
	        return users.toArray(User[]::new);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
	}
	
	public static User[] getValidator() {		
		return getUser("Validator");
		
	}
	
	public static User[] getVolunteer() {
		return getUser("Volunteer");
	}
	
	public static User getInitiator(int id) {
		String sqlSelectAllPersons = "SELECT * FROM User NATURAL JOIN Initiator WHERE User.ID=" + id + ";";

		try (ResultSet rs = executeQuery(sqlSelectAllPersons)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String name = rs.getString("FIRST_NAME");
		        String lastName = rs.getString("LAST_NAME");
		        int validator = rs.getInt("VALIDATOR");
		        
		        if(validator==0) {validator=-1;}
		            
		        return new Initiator(_id, name, lastName, validator);
		    }
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
	}

	public static User getValidator(int id) {
		String sqlSelectAllPersons = "SELECT * FROM User NATURAL JOIN Validator WHERE User.ID=" + id + ";";

		try (ResultSet rs = executeQuery(sqlSelectAllPersons)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String name = rs.getString("FIRST_NAME");
		        String lastName = rs.getString("LAST_NAME");
		            
		        return new Validator(_id, name, lastName);
		    }
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
	}

	public static User getVolunteer(int id) {
		String sqlSelectAllPersons = "SELECT * FROM User NATURAL JOIN Volunteer WHERE User.ID=" + id + ";";

		try (ResultSet rs = executeQuery(sqlSelectAllPersons)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String name = rs.getString("FIRST_NAME");
		        String lastName = rs.getString("LAST_NAME");
		            
		        return new Volunteer(_id, name, lastName);
		    }
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
	}
	
	public static void addValidator(int idInitiator, int idValidator){
		getInitiator(idInitiator);
		getValidator(idValidator);
		String sqlInserValidator = "UPDATE Initiator SET Validator=" + idValidator 
				+ " WHERE ID=" + idInitiator; 
		try {
			executeUpdate(sqlInserValidator);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
	}
	
	private static int addUser(User user, String type) {
		if (!user.isComplete()) {
			throw new RuntimeException("L'utilisateur n'est pas complet");
		}
		
		int id;
		if(user.hasID()) {
			id = user.getId();
		} else {
			id = getNextUserId();
		}
		
		String sqlInsertUser = "INSERT INTO User (ID, Last_Name, First_Name) VALUES "
				+ "(" + id + ", '" 
				+ user.getLastName() + "', '" 
				+ user.getFirstName()
				+ "');";
		String sqlInsertVolunteer =  "INSERT INTO "+ type + " (ID) VALUES "
				+ "(" + id+ ");";

		try {
			executeUpdate(sqlInsertUser);
			executeUpdate(sqlInsertVolunteer);
			return id;
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
			return -1;
		}
	}
	
	public static void addVolunteer(Volunteer volunteer) {
		addUser(volunteer, "Volunteer");
	}

	
	public static void addValidator(Validator validator) {
		addUser(validator, "Validator");
	}	
	
	public static void addInitiator(Initiator initiator) {
		int id = addUser(initiator, "Initiator");
		if (initiator.getValidator()!=-1) {
			addValidator(id, initiator.getValidator());
		}
	}	
	
}

