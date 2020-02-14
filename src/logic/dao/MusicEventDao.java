package logic.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import logic.entity.MusicEvent;
import logic.utils.*;

public class MusicEventDao extends DaoTemplate {

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String LOCATION = "location";
	private static final String BANDNAME = "band_name";
	private static final String ARTISTUSERNAME = "artist_username";
	private static final String TICKETONE = "ticketone";
	private static final String COVERPATH = "cover_path";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String DEFAULTPICTURE = "concert.jpg";
	private static final String DISTANCE = "distance_in_km";
	
	//Operations for the queries
	private static final String AROUNDYOU = "aroundyou";
	private static final String ACCEPT = "accepet";
	private static final String REJECT = "reject";
	private static final String ADDPART = "addpart";
	private static final String REMOVEPART = "removepart";
	
	private double lat = 0;
	private double lng = 0;
	private int rng = 0;
	
	public MusicEvent getMusicEvent(String id, String role) {
		return this.execute(new DaoAction<MusicEvent>() {
			@Override
			public MusicEvent act() throws ClassNotFoundException, SQLException {
				Connection conn = null;
				MusicEvent me = null;
				String sql = null;

				if(role.equals("admin")) {
					conn = DBAdminConnection.getAdminConnection();
					sql = "call livethemusic.get_pending_musicevent(?);\r\n";
				} else {
					conn = DBUserConnection.getUserConnection();
					sql = "call livethemusic.get_musicevent(?);\r\n";
				}

				try(PreparedStatement stm = conn.prepareStatement(sql)) {

					stm.setString(1, id);
					try (ResultSet rs = stm.executeQuery()) {
						rs.next();

						int idb = rs.getInt(ID);
						String name = rs.getString(NAME);
						String location = rs.getString(LOCATION);
						String bandName = rs.getString(BANDNAME);
						String artistUsername = rs.getString(ARTISTUSERNAME);
						String ticketone = rs.getString(TICKETONE);
						String coverPath = rs.getString(COVERPATH);

						//setting up coordinates of the musicevent
						double latitude = rs.getDouble(LATITUDE);
						double longitude = rs.getDouble(LONGITUDE);
						List<Double> coordinates = new ArrayList<>();
						coordinates.add(latitude);
						coordinates.add(longitude);

						if(coverPath == null || coverPath.equals("")) {
							coverPath = DEFAULTPICTURE;
						}
        	
						me = new MusicEvent(idb, artistUsername, name, coverPath, location, bandName, ticketone);
						me.setCoordinates(coordinates);
					}
				}
				return me;
			}
        });

	}
	
	public List<MusicEvent> getSuggestedEvents(String username){
        return this.queryDataBase(username, "suggested");
	}
	
	public List<MusicEvent> getPendingMusicEvents(){
        return this.queryDataBase("", "pending");
	}
	
	public List<MusicEvent> getSearchMusicEvent(String searchString){
        return this.queryDataBase(searchString, "search");
	}
	
	public void addParticipation(String username, String musicEventId) {
		this.manageParticipation(username, musicEventId, ADDPART);
	}
	
	public void removeParticipation(String username, String musicEventId) {
		this.manageParticipation(username, musicEventId, REMOVEPART);
	}
	
	public Boolean isParticipating(String username, String musicEventId) {
		Boolean ret = this.execute(new DaoAction<Boolean>() {
			@Override
			public Boolean act() throws ClassNotFoundException, SQLException {
				Connection conn = DBUserConnection.getUserConnection();
				String sql = "call livethemusic.is_participating(?, ?);\r\n"; 
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					stm.setString(1, username);
					stm.setInt(2,  Integer.parseInt(musicEventId));
					try (ResultSet rs = stm.executeQuery()) {
						return (rs.first());
					}
				}
			}
		});
		if (ret != null)
			return ret;
		else
			return false;
	}
	
	public boolean addMusicEvent(String name, String coverPath, String location, String artistUsername, Date date, String ticketone, List<Double> coordinates) {
		return (this.execute(new DaoAction<Boolean>() {
			@Override
			public Boolean act() throws ClassNotFoundException, SQLException {
				Connection con = DBArtistConnection.getArtistConnection();
				String sql = "call livethemusic.add_music_event(?, ?, ?, ?, ?, ?, ?, ?);\r\n"; 
				try (PreparedStatement stm = con.prepareStatement(sql)) {
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
					return true;
				}
			}
		}) != null);
	}

	public void acceptMusicEvent(String id) {
    	this.manageMusicEvent(id, ACCEPT);
	}

	public void rejectMusicEvent(String id) {
    	this.manageMusicEvent(id, REJECT);
	}
	
	public List<MusicEvent> getUserEvents(String username){
		return this.queryDataBase(username, "userevents");
	}
	
	public List<MusicEvent> getAroundYou(double latitude, double longitude, int range) {
		lat = latitude;
		lng = longitude;
		rng = range;
		return this.queryDataBase("", AROUNDYOU);
	}
	
	//User to round double numbers
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	//This method compacts three similar Data-Base queries
	private List<MusicEvent> queryDataBase(String string, String type) {
		List<MusicEvent> ret = this.execute(new DaoAction<List<MusicEvent>>() {
			@Override
			public List<MusicEvent> act() throws ClassNotFoundException, SQLException {
				Connection conn = null;
				List<MusicEvent> l = new ArrayList<>();
				PreparedStatement stm = null;
				try {
					if(type.equals("search")) {
						conn = DBUserConnection.getUserConnection();
						String sql = "call livethemusic.search_music_event(?);\r\n"; 
						stm = conn.prepareStatement(sql);
						//SearchString
						stm.setString(1, string);
					} else if(type.equals("suggested")) {
						conn = DBUserConnection.getUserConnection();
						String sql = "call livethemusic.view_friend_partitipation(?);\r\n"; 
						stm = conn.prepareStatement(sql);
						//Username
						stm.setString(1, string);
					} else if(type.equals("pending")) {
						conn = DBAdminConnection.getAdminConnection();
						String sql = "call livethemusic.view_pendingmusicevent();\r\n"; 
						stm = conn.prepareStatement(sql);
					} else if(type.equals(AROUNDYOU)) {
						conn = DBUserConnection.getUserConnection();
						String sql = "call livethemusic.around_you(?, ?, ?);\r\n";
						stm = conn.prepareStatement(sql);
						stm.setDouble(1, lat);
						stm.setDouble(2, lng);
						stm.setInt(3, rng);
					} else if(type.equals("userevents")) {
						conn = DBUserConnection.getUserConnection();
						String sql = "call livethemusic.view_user_events(?);\r\n";	
						stm = conn.prepareStatement(sql);
						stm.setString(1, string);
					}
			
					if(stm != null) {
						try (ResultSet rs = stm.executeQuery()) {
							l = unpackResultSet(rs, type);
						}
					}
				} finally {
					if (stm != null)
						stm.close();
				}
				return l;
			}
		});
		if (ret != null)
			return ret;
		else
			return Collections.emptyList();
	}
	
	private List<MusicEvent> unpackResultSet(ResultSet rs, String type) throws SQLException{
		List<MusicEvent> l = new ArrayList<>();
		
        if (!rs.first()) // rs not empty
            return Collections.emptyList();
		
        do{
        	int id = rs.getInt(ID);
        	String name = rs.getString(NAME);
        	String location = rs.getString(LOCATION);
        	String bandName = rs.getString(BANDNAME);
        	String artistUsername = rs.getString(ARTISTUSERNAME);
        	String coverPath = rs.getString(COVERPATH);
        	String ticketone = rs.getString(TICKETONE);
			double latitude = rs.getDouble(LATITUDE);
			double longitude = rs.getDouble(LONGITUDE);
			List<Double> coordinates = new ArrayList<>();
			coordinates.add(latitude);
			coordinates.add(longitude);

        	if(coverPath == null || coverPath.equals("")) {
        		coverPath = DEFAULTPICTURE;
        	}
        	MusicEvent temp = new MusicEvent(id, artistUsername, name, coverPath, location, bandName, ticketone);
        	temp.setCoordinates(coordinates);
        	
        	if(type.equals(AROUNDYOU)) {
        		double distance = rs.getDouble(DISTANCE);
            	temp.setDistance(this.round(distance, 2));
        	}
        	
        	l.add(temp);
        } while (rs.next());
        return l;
	}
	
	private void manageMusicEvent(String id, String operation) {
		this.execute(new DaoAction<Void>() {
			@Override
			public Void act() throws ClassNotFoundException, SQLException {
				Connection con = null;
				PreparedStatement stm = null;
				try {
					if(operation.equals(REJECT)) {
						con = DBAdminConnection.getAdminConnection();
						String sql = "call livethemusic.reject_musicevent(?);\r\n"; 
						stm = con.prepareStatement(sql);
						stm.setString(1, id);	
					} else if(operation.equals(ACCEPT)) {
						con = DBAdminConnection.getAdminConnection();
						String sql = "call livethemusic.accept_musicevent(?);\r\n"; 
						stm = con.prepareStatement(sql);
						stm.setString(1, id);
					}
    		
					if (stm != null) {
						stm.executeUpdate();
					}
				} finally {
					if (stm != null) {
						stm.close();
					}
				}
				return null;
			}
        });
	}
	
	private void manageParticipation(String username, String musicEventId, String operation) {
		this.execute(new DaoAction<Void>() {
			@Override
			public Void act() throws ClassNotFoundException, SQLException {
				Connection conn = null;
				PreparedStatement stm = null;
				String sql = null;
				try {
					conn = DBUserConnection.getUserConnection();
					if(operation.equals(ADDPART)) {
						sql = "call livethemusic.add_participation(?, ?);\r\n";
					} else if(operation.equals(REMOVEPART)) {
						sql = "call livethemusic.remove_participation(?, ?);\r\n"; 
					}
					stm = conn.prepareStatement(sql);
					stm.setString(1, username);
					stm.setInt(2,  Integer.parseInt(musicEventId));
					stm.executeUpdate();
				} finally {
					if (stm != null)
						stm.close();
				}
				return null;
            }
        });
	}
}
