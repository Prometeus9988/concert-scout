package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.MusicEvent;
import logic.utils.*;

public class MusicEventDao {
	
	private static final Logger logger = Logger.getLogger(MusicEventDao.class.getName());
	private static MusicEventDao instance = null;
	
	public MusicEventDao() {
		//TODO da mettere private e impostare tutto a singleton
	}
	
	public MusicEventDao getInstance() {
		if(instance == null) {
			instance = new MusicEventDao();
		}
		return instance;
	}
	
	public MusicEvent getMusicEvent(String id, String username) {
		Connection conn = null;
		MusicEvent me = null;
		try {
            conn = DBUserConnection.getUserConnection();

            ResultSet rs = Queries.selectMusicEvent(conn, id);
            
            if (!rs.first()) // rs not empty
                System.out.println("Empty");

            int idb = rs.getInt("id");
            String name = rs.getString("name");
            String location = rs.getString("location");
            String bandName = rs.getString("band_name");
            String artistUsername = rs.getString("artist_username");
            me = new MusicEvent(idb, artistUsername, name, "", location, bandName);
            

            rs.close();

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }
        
        return me;
	}
	
	public List<MusicEvent> getSuggestedEvents(String username){
        //PreparedStatement prepStmt = null;
        Connection conn = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();

            ResultSet rs = Queries.selectSuggestedMusicEvents(conn, username);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	int id = rs.getInt("id");
            	String name = rs.getString("name");
            	String location = rs.getString("location");
            	String bandName = rs.getString("band_name");
            	String artistUsername = rs.getString("artist_username");
            	l.add(new MusicEvent(id, artistUsername, name, "", location, bandName));
            } while (rs.next());
            rs.close();

        } catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }
        
        return l;
	}
	
	public List<MusicEvent> getSearchMusicEvent(String searchString){
        Connection conn = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            //prepStmt = conn.prepareStatement();
            ResultSet rs = Queries.selectSearchMusicEvent(conn, searchString);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	int id = rs.getInt("id");
            	String name = rs.getString("name");
            	String location = rs.getString("location");
            	String bandName = rs.getString("band_name");
            	String artistUsername = rs.getString("artist_username");
            	l.add(new MusicEvent(id, artistUsername, name, "", location, bandName));
            } while (rs.next());
            rs.close();

        } catch (SQLException se) {
        	// Errore durante l'apertura della connessione
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            	logger.log(Level.WARNING, se2.toString());
//            }
        }
        
        return l;
	}
	
	public void addParticipation(String username, String musicEventId) {
		Connection conn = null;
		try {
			conn = DBUserConnection.getUserConnection();
			Queries.addParticipation(conn, username, musicEventId);
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }
	}
	
	public void removeParticipation(String username, String musicEventId) {
		Connection conn = null;
		try {
			conn = DBUserConnection.getUserConnection();
			Queries.removeParticipation(conn, username, musicEventId);
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }
	}
	
	public boolean isParticipating(String username, String musicEventId) {
		Connection conn = null;
		try {
			conn = DBUserConnection.getUserConnection();
			ResultSet rs = Queries.isParticipating(conn, username, musicEventId);
            
			if (!rs.first()) { // rs empty no participation
				rs.close();
				return false;
			}
			else {			//not empty username participate	
				rs.close();
				return true;
			}
			
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }	
		return false;
	}
}
