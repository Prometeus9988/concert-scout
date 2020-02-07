package logic.dao;

import logic.entity.User;
import logic.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    
    public static boolean createUser(String username, String password, String firstName,
    		String lastName, String profilePicture, String email) {
    	Connection con = null;
    	PreparedStatement stm = null;
    	
    	try {
    		con = DBLoginConnection.getLoginConnection();
    		String sql = "call livethemusic.add_user(?, ?, ?, ?,?, ?);\r\n"; 
    		stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, firstName);
            stm.setString(3, lastName);
            stm.setString(4, email);
            stm.setString(5, password);
            stm.setString(6, profilePicture);
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
    
    public boolean isFollowing(String username, String artistId) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.is_following(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2,  artistId);
			stm.executeQuery();
			rs = stm.executeQuery();

			if (rs.first()) {
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

	public void follow(String username, String artist) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.follow_artist(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, username);
	        stm.setString(2, artist);
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
	
	public void unfollow(String username, String artist) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.unfollow_artist(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, username);
	        stm.setString(2, artist);
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
	
	public List<User> getSearchUser(String searchString, String caller){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        
        List<User> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            
            String sql = "call livethemusic.search_user(?, ?);\r\n";
    		stm = conn.prepareStatement(sql);
            stm.setString(1, searchString);
            stm.setString(2, caller);
    		rs = stm.executeQuery();
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	String username = rs.getString("username");
            	String name = rs.getString("name");
            	String surname = rs.getString("surname");
            	String profilePicture = rs.getString("profile_picture_path");
            	
            	if(profilePicture == null || profilePicture.equals("")) {
            		profilePicture = "concert.jpg";
            	}
            	
            	l.add(new User(username, name, surname, profilePicture));
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
	
	public List<User> getFriends(String username){
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        List<User> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();

    		String sql = "call livethemusic.view_friends(?);\r\n"; 
    		stm = conn.prepareStatement(sql);
            stm.setString(1, username);
           	rs = stm.executeQuery();
            
            if (!rs.first())
                return Collections.emptyList();
            
            do{
            	String usernamed = rs.getString("username");
            	String name = rs.getString("name");
            	String surname = rs.getString("surname");
            	String profilePicture = rs.getString("profile_picture_path");
            	
            	if(profilePicture == null || profilePicture.equals("")) {
            		profilePicture = "concert.jpg";
            	}
            	
            	l.add(new User(usernamed, name, surname, profilePicture));
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
	
	public boolean isFriend(String user, String target) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			
			String sql = "call livethemusic.is_friend(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, user);
	        stm.setString(2, target);
	        stm.executeQuery();
	       	rs = stm.executeQuery();
            
			if (rs.first()) { // rs empty not friends
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

	public void addFriend(String user, String target) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.add_friend(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, user);
	        stm.setString(2, target);
	        stm.executeUpdate();
	        stm.executeQuery();

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

	public void removeFriend(String user, String target) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			String sql = "call livethemusic.remove_friend(?, ?);\r\n"; 
			stm = conn.prepareStatement(sql);
	        stm.setString(1, user);
	        stm.setString(2, target);
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

}
