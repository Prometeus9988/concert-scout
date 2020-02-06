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
		List<UserBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			User curr = l.get(i);
			UserBean ub = new UserBean();
			ub.setUsername(curr.getUsername());
			ub.setName(curr.getName());
			ub.setProfilePicture(curr.getProfilePicture());
			lb.add(ub);
		}
		
		return lb;
	}
	
	public List<UserBean> getSearchUser(String searchString, String caller) {
		UserDao ud = new UserDao();

		List<User> l = ud.getSearchUser(searchString, caller);
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

	public boolean isFriend(GeneralUserBean user,UserBean ub) {
		UserDao ud = new UserDao();
		return ud.isFriend(user.getUsername(), ub.getUsername());
	}
	
	public void friend(GeneralUserBean user, UserBean ub) {
		UserDao ud = new UserDao();
		ud.addFriend(user.getUsername(), ub.getUsername());
	}
	
	public void unfriend(GeneralUserBean user, UserBean ub) {
		UserDao ud = new UserDao();
		ud.removeFriend(user.getUsername(), ub.getUsername());
	}

}
