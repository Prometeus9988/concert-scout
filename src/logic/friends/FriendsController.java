package logic.friends;

import java.util.ArrayList;
import java.util.List;

import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.entity.User;

public class FriendsController {
	
	public List<UserBean> getSearchUser(String searchString) {
		UserDao ud = new UserDao();

		List<User> l = ud.getSearchUser(searchString);
		List<UserBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new UserBean(l.get(i)));
		}
		
		return lb;
	}

}
