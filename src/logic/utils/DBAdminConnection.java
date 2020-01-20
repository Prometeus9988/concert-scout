package logic.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DBAdminConnection {
	
	private static Connection me=null;
	private static String user = "admin";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBAdminConnection() {
    	
    }
    
    public static Connection getAdminConnection() throws ClassNotFoundException, SQLException{
    	
    	if(me!=null) return me;
    	else {
    		Class.forName(driverClassName);
        	me=(Connection) DriverManager.getConnection(dbUrl,user,"admin");
    		return me;    		
    	}
    	
    }
    
    public static void closeAdminConnection() throws SQLException{
    	me.close();
    	me=null;
    }

}
