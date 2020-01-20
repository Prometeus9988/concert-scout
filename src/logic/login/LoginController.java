package logic.login;

import logic.utils.Queries;

public class LoginController {
	
	private static LoginController instance = null;
	
	private LoginController() {
	
	}
	
	public static LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	
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
}
