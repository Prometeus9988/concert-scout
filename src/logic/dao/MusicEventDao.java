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
	
	public List<MusicEvent> getSuggestedEvents(String username){
        //PreparedStatement prepStmt = null;
        Connection conn = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            //prepStmt = conn.prepareStatement();
            ResultSet rs = Queries.selectSuggestedMusicEvents(conn, username);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	int id = rs.getInt("id");
            	String name = rs.getString("name");
            	String location = rs.getString("location");
            	String bandName = rs.getString("band_name");
            	l.add(new MusicEvent(id, bandName, name, "", location));
            } while (rs.next());
            rs.close();

        } catch (SQLException se) {
        	// Errore durante l'apertura della connessione
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
//        } finally {
//           try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            	logger.log(Level.WARNING, se2.toString());
//            }
        }
        
        return l;
	}
	
	public List<MusicEvent> getSearchMusicEvent(String searchString){
        //PreparedStatement prepStmt = null;
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
            	l.add(new MusicEvent(id, bandName, name, "", location));
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
			/*int part = rs.getInt("participation");
			
			if(part == 1) {
				return true;
			}*/
			
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        }	
		return false;
	}
}
