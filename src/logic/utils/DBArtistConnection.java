package logic.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DBArtistConnection {
	
	private static Connection me=null;
	private static String user = "artist";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";

    private static String driverClassName = "com.mysql.jdbc.Driver";

    private DBArtistConnection() {
    	
    }
    
    public static Connection getArtistConnection() throws ClassNotFoundException, SQLException{
    	
    	if(me!=null) return me;
    	else {
    		Class.forName(driverClassName);
        	me=(Connection) DriverManager.getConnection(dbUrl,user,"artist");
    		return me;    		
    	}
    	
    }
    
    public static void closeArtistConnection() throws SQLException{
    	me.close();
    	me=null;
    }
	
}
