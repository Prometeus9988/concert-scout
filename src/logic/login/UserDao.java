package logic.login;

import logic.utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
	

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    private UserDao() {

    }
    
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
    	
    	//TODO check if the insert is successfull
    	return true;
    }
    
}
