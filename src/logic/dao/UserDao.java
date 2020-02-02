package logic.dao;

import logic.utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
	

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    
    public static boolean createUser(String username, String password, String firstName, String lastName, String email) {
    	Connection con = null;
    	try {
    		con = DBLoginConnection.getLoginConnection();
    		Queries.addUser(con, username, password, email, firstName, lastName);
    		
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
    
}
