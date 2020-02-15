package logic.fxmlview;

import logic.utils.Roles;

public class BackControllerFactory {
	
	private static BackControllerFactory myInstance=null;
	
	public static BackControllerFactory getInstance() {
		if(myInstance==null) {
			myInstance=new BackControllerFactory();
		}
		return myInstance;
	}
	
	public BackController creator(Roles role) {
		//ARTISTS DON'T NEED IT
		switch (role) {
		case USER:
			return new BackUserController();
		case ADMIN:
			return new BackAdminController();
		default:
			return null;
		}
	}

}
