package logic.followartist;

import logic.bean.GeneralUserBean;
import logic.bean.ArtistBean;
import logic.dao.UserDao;

public class FollowArtistController {
	public boolean isFollowing(GeneralUserBean user, ArtistBean ab) {
		UserDao ud = new UserDao();
		return ud.isFollowing(user.getUsername(), ab.getUsername());
	}

	public void follow(GeneralUserBean gu, ArtistBean ab) {
		UserDao ud = new UserDao();
		ud.follow(gu.getUsername(), ab.getUsername());
	} 
	
	public void unfollow(GeneralUserBean gu, ArtistBean ab) {
		UserDao ud = new UserDao();
		ud.unfollow(gu.getUsername(), ab.getUsername());
	} 
}
