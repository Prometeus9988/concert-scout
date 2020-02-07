package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Artist;
import logic.utils.*;

public class ArtistDao {
	
	private static final Logger logger = Logger.getLogger(ArtistDao.class.getName());
	private static final String DEFAULTPICTURE = "concert.jpg";
	
	// query fields
	private static final String USERNAME = "username";
	private static final String BANDNAME = "band_name";
	private static final String PROFILEPICTURE = "profile_picture_path";
	
	public List<Artist> getSuggestedArtist(String username){
		PreparedStatement stm = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Artist> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            String sql = "call livethemusic.followed_artists(?);\r\n"; 
            
            stm = conn.prepareStatement(sql);

            stm.setString(1, username);
            rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	String usernameA = rs.getString(USERNAME);
            	String bandName = rs.getString(BANDNAME);
            	String profilePicture = rs.getString(PROFILEPICTURE);
            	
            	if(profilePicture == null || profilePicture.equals("")) {
            		profilePicture = DEFAULTPICTURE;
            	}
            	
            	l.add(new Artist(usernameA, bandName, profilePicture));
            } while (rs.next());

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
            	if(rs != null){
            		rs.close();
            	}
            } catch (SQLException se1) {
            	logger.log(Level.WARNING, se1.toString());
            }
        	try {
            	if(stm != null) {
            		stm.close();
            	}
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
        
        return l;
	}
	
	public List<Artist> getSearchArtist(String searchString){
        PreparedStatement stm = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Artist> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            String sql = "call livethemusic.search_artist(?);\r\n";
            
            stm = conn.prepareStatement(sql);

            stm.setString(1, searchString);
            rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	String usernameA = rs.getString(USERNAME);
            	String bandName = rs.getString(BANDNAME);
            	String profilePicture = rs.getString(PROFILEPICTURE);
            	
            	if(profilePicture == null || profilePicture.equals("")) {
            		profilePicture = DEFAULTPICTURE;
            	}
            	
            	l.add(new Artist(usernameA, bandName, profilePicture));
            } while (rs.next());

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
            	if(rs != null){
            		rs.close();
            	}
            } catch (SQLException se1) {
            	logger.log(Level.WARNING, se1.toString());
            }
        	try {
            	if(stm != null) {
            		stm.close();
            	}
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
        
        return l;
	}
	
	public Artist getArtist(String username) {
		Connection conn = null;
		Artist a = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		
		try {
            conn = DBUserConnection.getUserConnection();

            String sql = "call livethemusic.get_artist(?);\r\n";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            
    		rs = stm.executeQuery();
    		
            rs.next();
            String usernamed = rs.getString(USERNAME);
            String profilePicture = rs.getString(PROFILEPICTURE);
            String bandName = rs.getString(BANDNAME);
            
        	if(profilePicture == null || profilePicture.equals("")) {
        		profilePicture = DEFAULTPICTURE;
        	}
        	
            a = new Artist(usernamed, bandName, profilePicture);

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
            	if(rs != null){
            		rs.close();
            	}
            } catch (SQLException se1) {
            	logger.log(Level.WARNING, se1.toString());
            }
        	try {
            	if(stm != null) {
            		stm.close();
            	}
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
        
        return a;
	}
	
	public static boolean createArtist(String username, String password, String bandName, String profilePicture, String email) {
    	Connection con = null;
    	PreparedStatement stm = null;
    	try {
    		con = DBLoginConnection.getLoginConnection();
    		String sql = "call livethemusic.add_artist(?, ?, ?, ?,?);\r\n"; 
    		stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, bandName);
            stm.setString(4, profilePicture);
            stm.setString(5, email);
            stm.executeUpdate();
    	} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        	return false;
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
            return false;
        } finally {
        	try {
            	if(stm != null) {
            		stm.close();
            	}
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
    	return true;
	}
}
