package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logic.entity.News;
import logic.utils.DBUserConnection;
import logic.utils.Queries;

public class NewsDao {
	public List<News> getNews(String username){
		Connection conn = null;
		ResultSet rs = null;
		List<News> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            rs = Queries.selectNews(conn, username);
            
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
        } catch (Exception e){
        	//TODO catch more adeguate exceptions
        	e.printStackTrace();
        }
        return l;
	}
}
