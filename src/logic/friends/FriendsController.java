package logic.friends;

import java.util.ArrayList;
import java.util.List;

import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.entity.User;

public class FriendsController {
	
	// TODO maybe split this controller
	
	public List<UserBean> getSearchUser(String searchString, String caller) {
		UserDao ud = new UserDao();

		List<User> l = ud.getSearchUser(searchString, caller);
		List<UserBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new UserBean(l.get(i)));
		}
		
		return lb;
	}

	public boolean isFriend(GeneralUserBean user,UserBean ub) {
		UserDao ud = new UserDao();
		return ud.isFriend(user.getUsername(), ub.getUsername());
	}

}
