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

import logic.entity.News;
import logic.utils.DBAdminConnection;
import logic.utils.DBArtistConnection;
import logic.utils.DBUserConnection;


public class NewsDao extends DaoTemplate {

	public static final String ACCEPT = "accept";
	public static final String REJECT = "reject";
	
	public List<News> getNews(String username, String role){
		List <News> ret = this.execute(new DaoAction<List<News>>() {
			@Override
			public List<News> execute() throws ClassNotFoundException, SQLException {
				Connection conn = null;
				String sql = null;
				PreparedStatement stm = null;
				List<News> l = new ArrayList<>();
				try {
					if (role.equals("admin")) {
						conn = DBAdminConnection.getAdminConnection();
						sql = "call livethemusic.view_pendingnews();\r\n";
						stm = conn.prepareStatement(sql);
					} else {
						conn = DBUserConnection.getUserConnection();
						sql = "call livethemusic.view_news(?);\r\n";
						stm = conn.prepareStatement(sql);
						stm.setString(1, username);
					}

					try (ResultSet rs = stm.executeQuery()) {

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
					}
				} finally {
					stm.close();
				}
				return l;
			}
		});
		if (ret != null)
			return ret;

		return Collections.emptyList();
	}

	public Boolean addNews(String text, String picturePath, String artistId, LocalDateTime current) {
		return (this.execute(new DaoAction<Boolean>() {
			@Override
			public Boolean execute() throws ClassNotFoundException, SQLException {
				Connection conn = DBArtistConnection.getArtistConnection();
			
				Timestamp timestamp = Timestamp.valueOf(current);
				String sql = "call livethemusic.add_news(?, ?, ?, ?);\r\n";
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					stm.setString(1, text);
					stm.setString(2, picturePath);
					stm.setString(3, artistId);
					stm.setTimestamp(4, timestamp);
					stm.executeUpdate();
					return true;
				}
			}
		}) != null);
	}
	
	public void manageNews(int id, String action) {
		this.execute(new DaoAction<Void>() {
			@Override
			public Void execute() throws ClassNotFoundException, SQLException {
				Connection conn = DBAdminConnection.getAdminConnection();
				String sql = null;
				if(action.equals(ACCEPT)) {
					sql = "call livethemusic.accept_news(?);\r\n";
				} else if(action.equals(REJECT)){
					sql = "call livethemusic.reject_news(?);\r\n";
				}
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					stm.setInt(1, id);
					stm.executeUpdate();
				}
				return null;
			}
		});
	}
}
