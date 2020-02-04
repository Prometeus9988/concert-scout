package logic.dao;

import logic.entity.User;
import logic.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    
    public static boolean createUser(String username, String password, String firstName,
    		String lastName, String profilePicture, String email) {
    	Connection con = null;
    	try {
    		con = DBLoginConnection.getLoginConnection();
    		Queries.addUser(con, username, password, email, firstName, lastName, profilePicture);
    		
    	} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        	return false;
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
            return false;
        }
    	return true;
    }
    
    public boolean isFollowing(String username, String musicEventId) {
		Connection conn = null;
		try {
			conn = DBUserConnection.getUserConnection();
			ResultSet rs = Queries.isFollowing(conn, username, musicEventId);
            
			if (!rs.first()) { // rs empty no participation
				rs.close();
				return false;
			}
			else {			//not empty username participate	
				rs.close();
				return true;
			}
			
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }	
		return false;
	}

	public void follow(String username1, String username2) {
		Connection conn = null;
		try {
			conn = DBUserConnection.getUserConnection();
			Queries.follow(conn, username1, username2);
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }
	}
	
	public void unfollow(String username1, String username2) {
		Connection conn = null;
		try {
			conn = DBUserConnection.getUserConnection();
			Queries.unfollow(conn, username1, username2);
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }
	}
	
	public List<User> getSearchUser(String searchString){
        Statement stmt = null;
        Connection conn = null;
        List<User> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            stmt = conn.createStatement();
            ResultSet rs = Queries.selectSearchUser(stmt, searchString);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	String username = rs.getString("username");
            	String name = rs.getString("name");
            	String surname = rs.getString("surname");
            	String profilePicture = rs.getString("profile_picture_path");
            	
            	if(profilePicture == null || profilePicture.equals("")) {
            		profilePicture = "concert.jpg";
            	}
            	
            	l.add(new User(username, name, surname, profilePicture));
            } while (rs.next());
            rs.close();

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
        return l;
	}
    
}
