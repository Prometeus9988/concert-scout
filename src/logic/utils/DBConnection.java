package logic.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnection {
	private static Connection me=null;
	private static String user = "default";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBConnection() {
    	
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
    	
    	if(me!=null) return me;
    	else {
    		Class.forName(driverClassName);
        	me=(Connection) DriverManager.getConnection(dbUrl,user,DBPasswordEncrypter.getPasswordDB());
    		return me;    		
    	}
    	
    }
    
    public static void closeConnection() throws SQLException{
    	me.close();
    	me=null;
    }
    
}
