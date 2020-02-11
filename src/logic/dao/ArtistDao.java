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
	private static final String SEARCH = "search";
	private static final String SUGGESTED = "suggested";
	
	public List<Artist> getSuggestedArtist(String username){
		return this.queryDataBase(username, SUGGESTED);
	}
	
	public List<Artist> getSearchArtist(String searchString){
		return this.queryDataBase(searchString, SEARCH);
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
            a = this.fetchTuple(rs);

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
	
	private List<Artist> queryDataBase(String string, String operation){
        PreparedStatement stm = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Artist> l = new ArrayList<>();
        String sql = null;
        try {
            conn = DBUserConnection.getUserConnection();
            
            if(operation.equals(SEARCH)) {
            	sql = "call livethemusic.search_artist(?);\r\n";
            } else if(operation.equals(SUGGESTED)) {
            	sql = "call livethemusic.followed_artists(?);\r\n";
            }
            
            stm = conn.prepareStatement(sql);

            stm.setString(1, string);
            rs = stm.executeQuery();
            
            l = this.unpackResultSet(rs);

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
	
	private List<Artist> unpackResultSet(ResultSet rs) throws SQLException{
		List<Artist> l = new ArrayList<>();
		if (!rs.first()) // rs not empty
            return Collections.emptyList();
        
        do{
        	l.add(this.fetchTuple(rs));
        } while (rs.next());
        return l;
	}
	
	private Artist fetchTuple(ResultSet rs) throws SQLException{
		String usernameA = rs.getString(USERNAME);
    	String bandName = rs.getString(BANDNAME);
    	String profilePicture = rs.getString(PROFILEPICTURE);
    	
    	if(profilePicture == null || profilePicture.equals("")) {
    		profilePicture = DEFAULTPICTURE;
    	}
    	
    	return new Artist(usernameA, bandName, profilePicture);
	}
}
