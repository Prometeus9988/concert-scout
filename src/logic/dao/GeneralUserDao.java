package logic.dao;

import logic.entity.GeneralUser;
import logic.utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralUserDao {
	

    private static final Logger logger = Logger.getLogger(GeneralUserDao.class.getName());

    private GeneralUserDao() {

    }
    
    public static GeneralUser findUser(String username, String password) {
        Connection conn = null;
        GeneralUser u = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            conn = DBLoginConnection.getLoginConnection();
            
            String sql = "call livethemusic.login(?, ?);\r\n";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
    		rs = stm.executeQuery();

            if (!rs.first()) // rs not empty
                return null;

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne;
            rs.first();

            String role = rs.getString("role");
            String usernameLoaded = rs.getString("username");
            
            if(usernameLoaded.equals(username)) {
            	u = new GeneralUser(usernameLoaded, "", role);
            }

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException se1) {
            	logger.log(Level.WARNING, se1.toString());
            }
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }

        return u;
    }
}
