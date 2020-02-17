package logic.login;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.dao.ArtistDao;
import logic.dao.GeneralUserDao;
import logic.dao.UserDao;
import logic.entity.GeneralUser;
import logic.exceptions.*;

public class LoginController {

	public GeneralUserBean login(GeneralUserBean userBean) throws LoginEmptyFieldException {
		GeneralUserDao gud = new GeneralUserDao();
		
		if(userBean.getUsername().equals("")) {
			throw new LoginEmptyFieldException("Username necessary");
		}
		if(userBean.getPassword().equals("")) {
			throw new LoginEmptyFieldException("Password necessary");
		}
		
		GeneralUser result = gud.findUser(userBean.getUsername(), userBean.getPassword());
		if (result == null)	{
			return null;
		} else {
			GeneralUserBean gu = new GeneralUserBean();
			gu.setUsername(result.getUsername());
			gu.setPassword(result.getPassword());
			gu.setRole(result.getRole());
			return gu;
		}
	}
	
	public boolean createUser(UserBean ub) {
		UserDao ud = new UserDao();
		return ud.createUser(ub.getUsername(), ub.getPassword(), ub.getName(), ub.getSurname(), ub.getProfilePicture(), ub.getEmail()); 
	}

	public boolean createArtist(ArtistBean ab) {
		ArtistDao ad = new ArtistDao();
		return ad.createArtist(ab.getUsername(), ab.getPassword(), ab.getBandName(), ab.getProfilePicture(), ab.getEmail());
	}
}
