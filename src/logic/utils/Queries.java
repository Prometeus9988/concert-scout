package logic.utils;

import java.sql.*;

public class Queries {
	
	private Queries() {
		
	}
	
	public static ResultSet selectGeneralUserLogin(Statement stm, String username, String password) throws SQLException{
        String sql = "SELECT username, role FROM general_user where username = '"
                + username + "' AND password = '" + password + "';";
		return stm.executeQuery(sql);
	}
	
	public static ResultSet selectSuggestedMusicEvents(Connection con, String username) throws SQLException {
        
		String sql = "call livethemusic.view_friend_partitipation(?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.executeUpdate();
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
	
	public static ResultSet selectSearchMusicEvent(Connection con, String searchString) throws SQLException {
        
		String sql = "call livethemusic.search_music_event(?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, searchString);
        stm.executeUpdate();
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
	
	public static ResultSet selectSearchArtist(Statement stm, String searchString) throws SQLException {
        String sql = "call livethemusic.search_artist('" + searchString + "');\r\n"; 
		return stm.executeQuery(sql);
	}
	
	public static ResultSet selectSuggestedArtist(Statement stm, String username) throws SQLException {
        String sql = "call livethemusic.followed_artists('" + username + "');\r\n"; 
		return stm.executeQuery(sql);
	}
	
	public static void addParticipation(Connection con, String username, String musicEventId) throws SQLException {
		String sql = "call livethemusic.add_participation(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	stm.executeQuery();
       	stm.closeOnCompletion(); 
	}
	
	public static void removeParticipation(Connection con, String username, String musicEventId) throws SQLException {
		String sql = "call livethemusic.remove_participation(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	stm.executeQuery();
       	stm.closeOnCompletion(); 
	}
	
	public static ResultSet isParticipating(Connection con, String username, String musicEventId) throws SQLException {
        
		String sql = "call livethemusic.is_participating(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
}
