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
	
	public static ResultSet selectSuggestedMusicEvents(Statement stm, String username) throws SQLException {
        String sql = "call livethemusic.view_friend_partitipation('" + username + "');\r\n"; 
		return stm.executeQuery(sql);
	}
	
	public static ResultSet selectSuggestedArtist(Statement stm, String username) throws SQLException {
        String sql = "call livethemusic.followed_artists('" + username + "');\r\n"; 
		return stm.executeQuery(sql);
	}
}
