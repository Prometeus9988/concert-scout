package logic.friends;

import java.util.ArrayList;
import java.util.List;

import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.entity.User;

public class FriendsController {
	
	// TODO maybe split this controller
	
	public List<UserBean> getFriends(String username){
		UserDao ud = new UserDao();
		List<User> l = ud.getFriends(username);
		return this.convert(l);
	}
	
	public List<UserBean> getRequests(String username){
		UserDao ud = new UserDao();
		List<User> l = ud.getFriendRequests(username);
		return this.convert(l);
	}
	
	public List<UserBean> getSearchUser(String searchString, String caller) {
		UserDao ud = new UserDao();

		List<User> l = ud.getSearchUser(searchString, caller);
		return this.convert(l);
	}

	public boolean isFriend(GeneralUserBean gb, UserBean ub) {
		UserDao ud = new UserDao();
		return ud.isFriend(gb.getUsername(), ub.getUsername());
	}
	
	public void requestFriend(GeneralUserBean gb, UserBean ub) {
		UserDao ud = new UserDao();
		ud.requestFriend(gb.getUsername(), ub.getUsername());
	}
	
	public void removeRequest(GeneralUserBean gb, UserBean ub) {
		UserDao ud = new UserDao();
		ud.removeFriendRequest(gb.getUsername(), ub.getUsername());
	}
	
	public void acceptRequest(GeneralUserBean gb, UserBean ub) {
		UserDao ud = new UserDao();
		ud.acceptFriendRequest(gb.getUsername(), ub.getUsername());
	}
	
	public void declineRequest(GeneralUserBean gb, UserBean ub) {
		UserDao ud = new UserDao();
		ud.removeFriendRequest(ub.getUsername(), gb.getUsername());
	}
	
	public String whoSentRequest(GeneralUserBean gb, UserBean ub) {
		// TODO check this
		UserDao ud = new UserDao();
		String user = gb.getUsername();
		String target = ub.getUsername();
		if (ud.isRequestSent(user, target))
			return "user";
		else if (ud.isRequestSent(target, user))
			return "target";
		else
			return "none";
	}
	
	public void unfriend(GeneralUserBean gb, UserBean ub) {
		UserDao ud = new UserDao();
		ud.removeFriend(gb.getUsername(), ub.getUsername());
	}

	private List<UserBean> convert(List<User> l){
		List<UserBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			User u = l.get(i);
			UserBean ub = new UserBean();
			ub.setUsername(u.getUsername());
			ub.setName(u.getName());
			ub.setSurname(u.getSurname());
			ub.setProfilePicture(u.getProfilePicture());
			lb.add(ub);
		}
		
		return lb;
	}
	
}
