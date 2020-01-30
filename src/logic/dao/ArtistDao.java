package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Artist;
import logic.utils.*;

public class ArtistDao {
	
	private static final Logger logger = Logger.getLogger(ArtistDao.class.getName());

	public List<Artist> getSuggestedArtist(String username){
        Statement stmt = null;
        Connection conn = null;
        List<Artist> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            stmt = conn.createStatement();
            ResultSet rs = Queries.selectSuggestedArtist(stmt, username);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	String usernameA = rs.getString("username");
            	String bandName = rs.getString("band_name");
            	String profilePicture = rs.getString("profile_picture_path");
 
            	l.add(new Artist(usernameA, bandName, profilePicture));
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
	
	public List<Artist> getSearchArtist(String searchString){
        Statement stmt = null;
        Connection conn = null;
        List<Artist> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            stmt = conn.createStatement();
            ResultSet rs = Queries.selectSearchArtist(stmt, searchString);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	//String usernameA = rs.getString("username");
            	String bandName = rs.getString("name");
            	//String profilePicture = rs.getString("profile_picture_path");
 
            	l.add(new Artist("", bandName, ""));
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
	
	public static boolean createArtist(String username, String password, String bandName, String profilePicture, String email) {
    	Connection con = null;
    	try {
    		con = DBLoginConnection.getLoginConnection();
    		Queries.addArtist(con, username, password, bandName, profilePicture, email);
    		
    	} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        	return false;
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
            return false;
        }
    	return true;
	}
}
