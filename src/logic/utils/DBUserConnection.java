package logic.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DBUserConnection {
	
	private static Connection me=null;
	private static String user = "user";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBUserConnection() {
    	
    }
    
    public static Connection getUserConnection() throws ClassNotFoundException, SQLException{
    	
    	if(me!=null) return me;
    	else {
    		Class.forName(driverClassName);
        	me=(Connection) DriverManager.getConnection(dbUrl,user,"user");
    		return me;    		
    	}
    	
    }
    
    public static void closeUserConnection() throws SQLException{
    	me.close();
    	me=null;
    }

}
