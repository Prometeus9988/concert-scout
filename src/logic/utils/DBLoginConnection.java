package logic.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBLoginConnection {
	private static Connection me=null;
	private static String user = "login";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBLoginConnection() {
    	
    }
    
    public static Connection getLoginConnection() throws ClassNotFoundException, SQLException{
    	
    	if(me!=null) return me;
    	else {
    		Class.forName(driverClassName);
        	me=(Connection) DriverManager.getConnection(dbUrl,user,"login");
    		return me;    		
    	}
    	
    }
    
    public static void closeLoginConnection() throws SQLException{
    	me.close();
    	me=null;
    }
    
}
