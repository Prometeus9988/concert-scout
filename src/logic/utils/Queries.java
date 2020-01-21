package logic.utils;

import java.sql.*;

public class Queries {
	
	private Queries() {
		
	}
	
	public static ResultSet selectGeneralUserLogin(Connection con, String username, String password) throws SQLException{
        String sql = "call livethemusic.login(?, ?);\r\n";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
		ResultSet rs = stm.executeQuery();
        stm.closeOnCompletion();
		return rs;
	}
	
	public static ResultSet selectSuggestedMusicEvents(Connection con, String username) throws SQLException {
        
		String sql = "call livethemusic.view_friend_partitipation(?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
	
	public static ResultSet selectSearchMusicEvent(Connection con, String searchString) throws SQLException {
        
		String sql = "call livethemusic.search_music_event(?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, searchString);
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
	
	public static ResultSet selectSearchArtist(Statement stm, String searchString) throws SQLException {
        String sql = "call livethemusic.search_artist('" + searchString + "');\r\n"; 
		ResultSet rs=stm.executeQuery(sql);
        stm.closeOnCompletion();
		return rs;
	}
	
	public static ResultSet selectSuggestedArtist(Statement stm, String username) throws SQLException {
        String sql = "call livethemusic.followed_artists('" + username + "');\r\n"; 
        ResultSet rs=stm.executeQuery(sql);
        stm.closeOnCompletion();
        return rs;
	}
	
	public static void addParticipation(Connection con, String username, String musicEventId) throws SQLException {
		String sql = "call livethemusic.add_participation(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	stm.closeOnCompletion(); 
	}
	
	public static void addUser(Connection con, String username, String password, String email, String firstName, String lastName ) throws SQLException {
		String sql = "call livethemusic.add_user(?, ?, ?, ?,?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, firstName);
        stm.setString(3, lastName);
        stm.setString(4, email);
        stm.setString(5, password);
        stm.executeUpdate();
       	stm.closeOnCompletion(); 
	}
	
	public static void addArtist(Connection con, String username, String password, String bandName, String profilePicture, String email ) throws SQLException {
		String sql = "call livethemusic.add_artist(?, ?, ?, ?,?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        stm.setString(3, bandName);
        stm.setString(4, profilePicture);
        stm.setString(5, email);
        stm.executeUpdate();
       	stm.closeOnCompletion(); 
	}
	
	public static void removeParticipation(Connection con, String username, String musicEventId) throws SQLException {
		String sql = "call livethemusic.remove_participation(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	stm.closeOnCompletion(); 
	}
	
	public static ResultSet isParticipating(Connection con, String username, String musicEventId) throws SQLException {
        
		String sql = "call livethemusic.is_participating(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeQuery();
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
}
