package logic.login;

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
	
	public User login(String username, String password) {
        return UserDao.findUser(username, password);
    }
	
	public GeneralUserBean login(GeneralUserBean userBean) {
		return new GeneralUserBean(GeneralUserDao.findUser(userBean.getUsername(), userBean.getPassword()));
    }
}
