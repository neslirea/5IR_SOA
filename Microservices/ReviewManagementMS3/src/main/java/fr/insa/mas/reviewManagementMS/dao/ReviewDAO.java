package fr.insa.mas.reviewManagementMS.dao;

import java.sql.Connection;
import java.time.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fr.insa.mas.reviewManagementMS.Review;

import java.sql.Statement;


public class ReviewDAO {
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
	
	
	
	public static void addReview(Review review){			
		String sqlInsertMission = "INSERT INTO Review "
				+ "(NOTE, MISSION, DESCRIPTION) "  
				+ "VALUES ( " 
				+ review.getNote() +", " 
				+ review.getMission() +", "
				+ "'" + review.getDescription() +"' "
				+ "); "; 
		try {
			executeUpdate(sqlInsertMission);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
	} 
	
	
	public static Review[] getReviews(int id_volunteer) {
		String sqlGetReview = "SELECT * FROM Mission "
				+ " JOIN Review ON Review.MISSION=Mission.ID"
				+ " WHERE Mission.VOLUNTEER=" + id_volunteer
				+ "; "; 
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		try (ResultSet rs = executeQuery(sqlGetReview)){
	        while (rs.next()) {
	        	int id = rs.getInt("ID");
		        int note = rs.getInt("NOTE");
		        int mission = rs.getInt("MISSION");
		        String description = rs.getString("Review.DESCRIPTION");
		          
		        reviews.add(new Review(id, note, mission, description));
		    }
	        
	        return reviews.toArray(Review[]::new);
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return null;
	}

	public static int getMeanReviews(int id_volunteer) {
		String sqlGetReview = "SELECT AVG(NOTE) as mean FROM Mission "
				+ " JOIN Review ON Review.MISSION=Mission.ID"
				+ " WHERE Mission.VOLUNTEER=" + id_volunteer
				+ "; "; 
		
		try (ResultSet rs = executeQuery(sqlGetReview)){
	        rs.next();
        	int mean = rs.getInt("mean");	    
	        
	        return mean;
		} catch (SQLException e) {
		    // handle the exception
			System.err.println(e);
		}
		return -1;
	}

	
	
}

