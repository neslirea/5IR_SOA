package fr.insa.mas.missionManagementMS.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import fr.insa.mas.missionManagementMS.Mission;
import fr.insa.mas.missionManagementMS.Status;


public class MissionDAO {
	private static java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	
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
	
	public static Mission[] getMission() {
		String sqlSelectMission = "SELECT * FROM Mission NATURAL JOIN Status;";

		ArrayList<Mission> missions = new ArrayList<Mission>();

		try (ResultSet rs = executeQuery(sqlSelectMission)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String titre = rs.getString("TITRE");
		        String description = rs.getString("DESCRIPTION");
		        Status status = getStatus(rs.getInt("STATUS"));
		        Date creationDate = new Date(rs.getTimestamp("CREATION_DATE").getTime());
		        Timestamp ts = rs.getTimestamp("FINISH_DATE");
		        Date finishDate = null;
		        if(ts!=null) {
		        	finishDate = new Date(ts.getTime());
		        }
		        Integer initiator = rs.getInt("INITIATOR");
		        Integer volunteer = rs.getInt("VOLUNTEER");
		        
		        if(initiator==0) {initiator=null;}
		        if(volunteer==0) {volunteer=null;}
		            
		        missions.add(new Mission(_id, titre, description, creationDate, finishDate, initiator, volunteer, status));
		    }
	        return missions.toArray(Mission[]::new);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
		
	}
	
	
	public static Mission getMission(int id) {
		String sqlSelectMission = "SELECT * FROM Mission NATURAL JOIN Status WHERE Mission.ID=" + id + ";";

		try (ResultSet rs = executeQuery(sqlSelectMission)){
	        while (rs.next()) {
	        	int _id = rs.getInt("ID");
		        String titre = rs.getString("TITRE");
		        String description = rs.getString("DESCRIPTION");
		        Status status = getStatus(rs.getInt("STATUS"));
		        Date creationDate = new Date(rs.getTimestamp("CREATION_DATE").getTime());
		        Timestamp ts = rs.getTimestamp("FINISH_DATE");
		        Date finishDate = null;
		        if(ts!=null) {
		        	finishDate = new Date(ts.getTime());
		        }
		        int initiator = rs.getInt("INITIATOR");
		        int volunteer = rs.getInt("VOLUNTEER");
		            
		        return new Mission(_id, titre, description, creationDate, finishDate, initiator, volunteer, status);
		    }
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
		
	}
	
	private static int getStatus(Status status) {
		String sqlSelectMission = "SELECT ID FROM Status WHERE LABEL='" + status.toString() + "';";

		try (ResultSet rs = executeQuery(sqlSelectMission)){
	        rs.next();
	        int nb = rs.getInt("ID");
	        return nb;
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
			return -1;
		}
	}
	
	private static Status getStatus(int id) {
		String sqlSelectMission = "SELECT LABEL FROM Status WHERE id='" + id + "';";

		try (ResultSet rs = executeQuery(sqlSelectMission)){
	        rs.next();
	        Status status = Status.valueOf(rs.getString("LABEL"));
	        return status;
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
			return null;
		}
	}

	
	public static void addMission(Mission mission){			
		String sqlInsertMission = "INSERT INTO Mission "
				+ "(TITRE, DESCRIPTION, CREATION_DATE, STATUS, INITIATOR) "  
				+ "VALUES ( " 
				+ "'" + mission.getTitre() +"', " 
				+ "'" + mission.getDescription() +"', "
				+ "'" + sdf.format(new Date())  +"', "
				+ getStatus(mission.getStatus()) +", "
				+ mission.getInitiator()
				+ "); "; 
		try {
			executeUpdate(sqlInsertMission);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
	} 
	
	
	public static void updateStatus(int id_mission, Status status) {
		String sqlUpdateMission = "UPDATE Mission "
				+ "SET STATUS=" + getStatus(status)  
				+ " WHERE ID= " + id_mission
				+ "; "; 
		try {
			executeUpdate(sqlUpdateMission);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
	}
	
	public static void assignMission(int id_mission, int id_volunteer, Status status) {
		String sqlUpdateMission = "UPDATE Mission "
				+ "SET VOLUNTEER=" + id_volunteer 
				+ ", STATUS=" + getStatus(status)
				+ " WHERE ID= " + id_mission
				+ "; "; 
		try {
			executeUpdate(sqlUpdateMission);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
	}
	
	public static void finishMission(int id_mission, Status status) {
		String sqlUpdateMission = "UPDATE Mission "
				+ "SET FINISH_DATE=" +  "'" + sdf.format(new Date()) +"'"
				+ ", STATUS=" + getStatus(status)
				+ " WHERE ID= " + id_mission
				+ "; "; 
		try {
			executeUpdate(sqlUpdateMission);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
	}
	
	
}

