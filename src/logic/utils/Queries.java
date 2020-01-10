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
}
