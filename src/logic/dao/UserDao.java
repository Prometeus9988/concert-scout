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
	private static final String FOLLOW = "follow";
	private static final String UNFOLLOW = "unfollow";
	private static final String REMOVEFRIEND = "removefriend";
	private static final String ACCEPTFRIENDREQUEST = "acceptfriendrequest";
	private static final String REMOVEFRIENDREQUEST = "removefrinedrequest";
	private static final String REQUESTFRIEND = "requestfriend";
	private static final String SEARCHUSER = "searchuser";
	private static final String VIEWFRIENDS = "viewfriends";
	private static final String VIEWFRIENDSREQUESTS = "viewfriendsrequests";
	private static final String SEARCHFRIENDREQUEST = "searchfriendrequest";
	private static final String ISFRIEND = "isfriend";
	private static final String ISFOLLOWING = "isfollowing";
    
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
		return this.isQueryDataBase(username, artistId, ISFOLLOWING);
	}

	public void follow(String username, String artist) {
		this.manageFollow(username, artist, FOLLOW);
	}
	
	public void unfollow(String username, String artist) {
		this.manageFollow(username, artist, UNFOLLOW);
	}
	
	public List<User> getSearchUser(String searchString, String caller){
        return this.queryDatabase(searchString, caller, SEARCHUSER);
	}
	
	public List<User> getFriends(String username){
        return this.queryDatabase(username, "", VIEWFRIENDS);
	}
	
	public List<User> getFriendRequests(String username){
        return this.queryDatabase(username, "", VIEWFRIENDSREQUESTS);
	}
	
	public boolean isFriend(String user, String target) {
		return this.isQueryDataBase(user, target, ISFRIEND);
	}
	
	public boolean isRequestSent(String user, String target) {
		return this.isQueryDataBase(user, target, SEARCHFRIENDREQUEST);
	}

	public void requestFriend(String user, String target) {
		this.manageFriends(user, target, REQUESTFRIEND);
	}
	
	public void removeFriendRequest(String user, String target) {
		this.manageFriends(user, target, REMOVEFRIENDREQUEST);
	}
	
	public void acceptFriendRequest(String user, String target) {
		this.manageFriends(user, target, ACCEPTFRIENDREQUEST);
	}

	public void removeFriend(String user, String target) {
		this.manageFriends(user, target, REMOVEFRIEND);
	}

	public void manageFollow(String username, String artist, String operation) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			
			if(operation.equals(FOLLOW)) {
				sql = "call livethemusic.follow_artist(?, ?);\r\n"; 
			} else if(operation.equals(UNFOLLOW)) {
				sql = "call livethemusic.unfollow_artist(?, ?);\r\n";
			}
			
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
	
	public void manageFriends(String user, String target, String operation) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			if(operation.equals(REMOVEFRIEND)) {
				sql = "call livethemusic.remove_friend(?, ?);\r\n";
			} else if(operation.equals(ACCEPTFRIENDREQUEST)) {
				sql = "call livethemusic.accept_friend_request(?, ?);\r\n";
			} else if(operation.equals(REMOVEFRIENDREQUEST)) {
				sql = "call livethemusic.remove_friend_request(?, ?);\r\n"; 
			} else if(operation.equals(REQUESTFRIEND)){
				sql = "call livethemusic.request_friend(?, ?);\r\n";
			}
			
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
	
	public List<User> queryDatabase(String string, String caller, String operation){
		Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        
        List<User> l = new ArrayList<>();
        try {
            conn = DBUserConnection.getUserConnection();
            if(operation.equals(SEARCHUSER)) {
            	String sql = "call livethemusic.search_user(?, ?);\r\n";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, string);
            	stm.setString(2, caller);
            } else if(operation.equals(VIEWFRIENDS)) {
        		String sql = "call livethemusic.view_friends(?);\r\n"; 
        		stm = conn.prepareStatement(sql);
                stm.setString(1, string);
            } else if(operation.equals(VIEWFRIENDSREQUESTS)) {
        		String sql = "call livethemusic.view_friend_requests(?);\r\n"; 
        		stm = conn.prepareStatement(sql);
                stm.setString(1, string);
            }
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
	
	
	private boolean isQueryDataBase(String user, String target, String operation) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		String sql = null;
		
		try {
			conn = DBUserConnection.getUserConnection();
			
			if(operation.equals(SEARCHFRIENDREQUEST)) {
				sql = "call livethemusic.search_friend_request(?, ?);\r\n";
			} else if(operation.equals(ISFRIEND)) {
				sql = "call livethemusic.is_friend(?, ?);\r\n"; 
			} else if(operation.equals(ISFOLLOWING)) {
				sql = "call livethemusic.is_following(?, ?);\r\n";
			}
			stm = conn.prepareStatement(sql);
	        stm.setString(1, user);
	        stm.setString(2, target);
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
}
