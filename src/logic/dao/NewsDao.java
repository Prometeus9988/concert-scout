package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.News;
import logic.utils.DBUserConnection;


public class NewsDao {
	private static final Logger logger = Logger.getLogger(NewsDao.class.getName());
	
	public List<News> getNews(String username){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		List<News> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
    		String sql = "call livethemusic.view_news(?);\r\n"; 
    		stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.executeQuery();
           	rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();	//return empty list
            
            do{
            	String text = rs.getString("Text");
            	String bandName = rs.getString("band_name");
            	String usernameB = rs.getString("username");
            	Timestamp timestamp = rs.getTimestamp("time");
            	String picturePath = rs.getString("picture_path");
            	String profilePath = rs.getString("profile_picture_path");
            	int id = rs.getInt("id");
            	
            	//Conversion from timestamp to LocalDateTime used by the system
            	LocalDateTime dateTime = LocalDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.ofHours(0));
            	
            	if(picturePath == null) {
            		picturePath = "";
            	}
            	
            	if(profilePath == null || profilePath.equals("")) {
            		profilePath = "concert.jpg";
            	}
            	
            	l.add(new News(id, text, usernameB, dateTime, picturePath, bandName, profilePath));
            } while (rs.next());
            rs.close(); 
        } catch (SQLException se){
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
        	try {
                if (rs != null)
                    rs.close();
            } catch (SQLException se1) {
            	logger.log(Level.WARNING, se1.toString());
            }
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
        return l;
	}
}
