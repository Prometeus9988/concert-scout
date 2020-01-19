package logic.utils;

import java.sql.*;

public class Queries {
	
	private Queries() {
		
	}
	
	public static ResultSet selectGeneralUserLogin(Statement stm, String username, String password) throws SQLException{
        String sql = "SELECT username, role FROM general_user where username = '"
                + username + "' AND password = '" + password + "';";
		ResultSet rs=stm.executeQuery(sql);
        stm.closeOnCompletion();
		return rs;
	}
	
	public static ResultSet selectSuggestedMusicEvents(Connection con, String username) throws SQLException {
        
		String sql = "call LIVEtheMUSIC.view_friend_partitipation(?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
	
	public static ResultSet selectSearchMusicEvent(Connection con, String searchString) throws SQLException {
        
		String sql = "call LIVEtheMUSIC.search_music_event(?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, searchString);
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
	
	public static ResultSet selectSearchArtist(Statement stm, String searchString) throws SQLException {
        String sql = "call LIVEtheMUSIC.search_artist('" + searchString + "');\r\n"; 
		ResultSet rs=stm.executeQuery(sql);
        stm.closeOnCompletion();
		return rs;
	}
	
	public static ResultSet selectSuggestedArtist(Statement stm, String username) throws SQLException {
        String sql = "call LIVEtheMUSIC.followed_artists('" + username + "');\r\n"; 
        ResultSet rs=stm.executeQuery(sql);
        stm.closeOnCompletion();
        return rs;
	}
	
	public static void addParticipation(Connection con, String username, String musicEventId) throws SQLException {
		String sql = "call LIVEtheMUSIC.add_participation(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	stm.closeOnCompletion(); 
	}
	
	public static void removeParticipation(Connection con, String username, String musicEventId) throws SQLException {
		String sql = "call LIVEtheMUSIC.remove_participation(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeUpdate();
       	stm.closeOnCompletion(); 
	}
	
	public static ResultSet isParticipating(Connection con, String username, String musicEventId) throws SQLException {
        
		String sql = "call LIVEtheMUSIC.is_participating(?, ?);\r\n"; 
		PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2,  Integer.parseInt(musicEventId));
        stm.executeQuery();
       	ResultSet rs = stm.executeQuery();
       	stm.closeOnCompletion();
       	return rs;
	}
}
