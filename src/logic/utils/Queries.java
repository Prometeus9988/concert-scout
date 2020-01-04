package logic.utils;

import java.sql.*;

public class Queries {
	
	private Queries() {
		
	}
	
	public static ResultSet selectUserLogin(Statement stm,String username, String password) throws SQLException{
        String sql = "SELECT name, surname, username, password FROM user where username = '"
                + username + "' AND password = '" + password + "';";
		return stm.executeQuery(sql);
	}
}
