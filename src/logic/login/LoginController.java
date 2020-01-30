package logic.login;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.dao.ArtistDao;
import logic.dao.GeneralUserDao;
import logic.dao.UserDao;
import logic.entity.GeneralUser;
import logic.utils.Queries;

public class LoginController {

	public static void main(String[] args) {
		System.out.println("To Implement");
	}
	
	public GeneralUserBean login(GeneralUserBean userBean) {
		GeneralUser result = GeneralUserDao.findUser(userBean.getUsername(), userBean.getPassword());
		if(result == null)	return null;
		else return new GeneralUserBean(result);
	}
	
	public boolean createUser(UserBean ub) {
		return UserDao.createUser(ub.getUsername(), ub.getPassword(), ub.getName(), ub.getSurname(), ub.getEmail()); 
	}
	
	public boolean createArtist(ArtistBean ab) {
		return ArtistDao.createArtist(ab.getUsername(), ab.getPassword(), ab.getBandName(), ab.getProfilePicture(), ab.getEmail());
	}
}
