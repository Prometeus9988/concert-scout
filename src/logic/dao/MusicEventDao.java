package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.MusicEvent;
import logic.utils.*;

public class MusicEventDao {
	
	private static final Logger logger = Logger.getLogger(MusicEventDao.class.getName());
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String LOCATION = "location";
	private static final String BANDNAME = "band_name";
	private static final String ARTISTUSERNAME = "artist_username";
	private static final String TICKETONE = "ticketone";
	private static final String COVERPATH = "cover_path";
	private static final String DEFAULTPICTURE = "concert.jpg";
	
	public MusicEvent getMusicEvent(String id, String role) {
		Connection conn = null;
		MusicEvent me = null;
		ResultSet rs = null;
		String sql = null;
		PreparedStatement stm = null;
		
		try {
			if(role.equals("admin")) {
				conn = DBAdminConnection.getAdminConnection();
				sql = "call livethemusic.get_pending_musicevent(?);\r\n";
			} else {
				conn = DBUserConnection.getUserConnection();
		        sql = "call livethemusic.get_musicevent(?);\r\n";
			}

			stm = conn.prepareStatement(sql);
			
	        stm.setString(1, id);
			rs = stm.executeQuery();
			rs.next();
			
            int idb = rs.getInt(ID);
            String name = rs.getString(NAME);
            String location = rs.getString(LOCATION);
            String bandName = rs.getString(BANDNAME);
            String artistUsername = rs.getString(ARTISTUSERNAME);
            String ticketone = rs.getString(TICKETONE);
            String coverPath = rs.getString(COVERPATH);
            
        	if(coverPath == null || coverPath.equals("")) {
        		coverPath = DEFAULTPICTURE;
        	}
            me = new MusicEvent(idb, artistUsername, name, coverPath, location, bandName, ticketone);

        } catch (SQLException se) {
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
        return me;
	}
	
	public List<MusicEvent> getSuggestedEvents(String username){
        Connection conn = null;
    	ResultSet rs = null;
    	PreparedStatement stm = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();

    		String sql = "call livethemusic.view_friend_partitipation(?);\r\n"; 
    		stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	int id = rs.getInt(ID);
            	String name = rs.getString(NAME);
            	String location = rs.getString(LOCATION);
            	String bandName = rs.getString(BANDNAME);
            	String artistUsername = rs.getString(ARTISTUSERNAME);
            	String coverPath = rs.getString(COVERPATH);
            	if(coverPath == null || coverPath.equals("")) {
            		coverPath = DEFAULTPICTURE;
            	}
            	l.add(new MusicEvent(id, artistUsername, name, coverPath, location, bandName, ""));
            } while (rs.next());

        } catch (SQLException se) {
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
	
	public List<MusicEvent> getPendingMusicEvent(){
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
			conn = DBAdminConnection.getAdminConnection();

			String sql = "call livethemusic.view_pendingmusicevent();\r\n"; 
			stm = conn.prepareStatement(sql);
	       	rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	int id = rs.getInt(ID);
            	String name = rs.getString(NAME);
            	String location = rs.getString(LOCATION);
            	String bandName = rs.getString(BANDNAME);
            	String artistUsername = rs.getString(ARTISTUSERNAME);
            	String ticketone = rs.getString(TICKETONE);
            	String coverPath = rs.getString(COVERPATH);
            	if(coverPath == null || coverPath.equals("")) {
            		coverPath = DEFAULTPICTURE;
            	}
            	l.add(new MusicEvent(id, artistUsername, name, coverPath, location, bandName, ticketone));
            } while (rs.next());

        } catch (SQLException se) {
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
	
	public List<MusicEvent> getSearchMusicEvent(String searchString){
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();

            String sql = "call livethemusic.search_music_event(?);\r\n"; 
            stm = conn.prepareStatement(sql);
            stm.setString(1, searchString);
            rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	int id = rs.getInt(ID);
            	String name = rs.getString(NAME);
            	String location = rs.getString(LOCATION);
            	String bandName = rs.getString(BANDNAME);
            	String artistUsername = rs.getString(ARTISTUSERNAME);
            	String coverPath = rs.getString(COVERPATH);
            	
            	if(coverPath == null || coverPath.equals("")) {
            		coverPath = DEFAULTPICTURE;
            	}
            	l.add(new MusicEvent(id, artistUsername, name, coverPath, location, bandName, ""));
            } while (rs.next());

        } catch (SQLException se) {
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
	
	public void addParticipation(String username, String musicEventId) {
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.add_participation(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, username);
	        stm.setInt(2,  Integer.parseInt(musicEventId));
	        stm.executeUpdate();

		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
	}
	
	public void removeParticipation(String username, String musicEventId) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.remove_participation(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, username);
	        stm.setInt(2,  Integer.parseInt(musicEventId));
	        stm.executeUpdate();
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
	}
	
	public boolean isParticipating(String username, String musicEventId) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.is_participating(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, username);
	        stm.setInt(2,  Integer.parseInt(musicEventId));
	       	rs = stm.executeQuery();
            
			if (!rs.first()) {
				rs.close();
				return false;
			} else {
				rs.close();
				return true;
			}
			
		} catch (SQLException se) {
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
		return false;
	}
	
	public boolean addMusicEvent(String name, String coverPath, String location, String artistUsername, Date date, String ticketone, List<Double> coordinates) {
    	Connection con = null;
    	PreparedStatement stm = null;
    	
    	try {
    		con = DBArtistConnection.getArtistConnection();
    		String sql = "call livethemusic.add_music_event(?, ?, ?, ?, ?, ?, ?, ?);\r\n"; 
    		stm = con.prepareStatement(sql);
    		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    		stm.setString(1, name);
    		stm.setString(2, coverPath);
    		stm.setString(3, location);
    		stm.setString(4, artistUsername);
    		stm.setDate(5, sqlDate);
    		stm.setString(6, ticketone);
    		stm.setDouble(7, coordinates.get(0));
    		stm.setDouble(8, coordinates.get(1));
    		stm.executeUpdate();
    		
    	} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        	return false;
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
            return false;
        } finally {
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
    	return true;
	}

	public void acceptMusicEvent(String id) {
    	Connection con = null;
    	PreparedStatement stm = null;
    	try {
    		con = DBAdminConnection.getAdminConnection();
    		String sql = "call livethemusic.accept_musicevent(?);\r\n"; 
    		stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
    		
    	} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
	}

	public void rejectMusicEvent(String id) {
    	Connection con = null;
    	PreparedStatement stm = null;
    	try {
    		con = DBAdminConnection.getAdminConnection();
    		String sql = "call livethemusic.reject_musicevent(?);\r\n"; 
    		stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
    		
    	} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
	}
	
	public List<MusicEvent> getUserEvents(String username){
		Connection conn = null;
		List<MusicEvent> l = new ArrayList<>();
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.view_user_events(?);\r\n";
			
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			
			rs = stm.executeQuery();

			if(!rs.first()) {
				return Collections.emptyList();
			}

            do{
            	int id = rs.getInt(ID);
            	String name = rs.getString(NAME);
            	String location = rs.getString(LOCATION);
            	String bandName = rs.getString(BANDNAME);
            	String artistUsername = rs.getString(ARTISTUSERNAME);
            	String coverPath = rs.getString(COVERPATH);
            	
            	if(coverPath == null || coverPath.equals("")) {
            		coverPath = DEFAULTPICTURE;
            	}
            	l.add(new MusicEvent(id, artistUsername, name, coverPath, location, bandName, ""));
            } while (rs.next());
			
			
			
		} catch (SQLException se) {
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
