package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logic.entity.Artist;
import logic.utils.*;

public class ArtistDao extends DaoTemplate {

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
		return this.execute(new DaoAction<Artist>() {
			@Override
			public Artist act() throws ClassNotFoundException, SQLException {
				Connection conn = DBUserConnection.getUserConnection();
				Artist a = null;

				String sql = "call livethemusic.get_artist(?);\r\n";
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					stm.setString(1, username);
            
					try (ResultSet rs = stm.executeQuery()) {

						rs.next();
						a = fetchTuple(rs);
					}
            	}
				return a;
            }
		});
	}
	
	public Boolean createArtist(String username, String password, String bandName, String profilePicture, String email) {
		return (this.execute(new DaoAction<Boolean>() {
			@Override
			public Boolean act() throws ClassNotFoundException, SQLException {
				Connection con =  DBLoginConnection.getLoginConnection();
				String sql = "call livethemusic.add_artist(?, ?, ?, ?,?);\r\n"; 
				try (PreparedStatement stm = con.prepareStatement(sql)) {
					stm.setString(1, username);
					stm.setString(2, password);
					stm.setString(3, bandName);
					stm.setString(4, profilePicture);
					stm.setString(5, email);
					stm.executeUpdate();
					return true;
				}
			}
		}) != null);
	}
	
	private List<Artist> queryDataBase(String string, String operation) {
		List<Artist> ret = this.execute(new DaoAction<List<Artist>>() {
			@Override
			public List<Artist> act() throws ClassNotFoundException, SQLException {
				Connection conn = DBUserConnection.getUserConnection();
				String sql = null;
				List<Artist> l = new ArrayList<>();
				if(operation.equals(SEARCH)) {
					sql = "call livethemusic.search_artist(?);\r\n";
				} else if(operation.equals(SUGGESTED)) {
					sql = "call livethemusic.followed_artists(?);\r\n";
				}
            
				try (PreparedStatement stm = conn.prepareStatement(sql)) {

					stm.setString(1, string);
					try (ResultSet rs = stm.executeQuery()) {
						l = unpackResultSet(rs);
					}
				}
				return l;
			}
		});
		if (ret != null)
			return ret;
		else
			return Collections.emptyList();
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
