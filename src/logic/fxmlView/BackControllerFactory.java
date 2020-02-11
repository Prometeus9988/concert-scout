package logic.fxmlView;

public class BackControllerFactory {
	
	private static BackControllerFactory myInstance=null;
	
	public static BackControllerFactory getInstance() {
		if(myInstance==null) {
			myInstance=new BackControllerFactory();
		}
		return myInstance;
	}
	
	public BackController creator(int kind) {
		
		//DUMMY FOR NOW
		//THEN INTRODUCING ADMIN BACK CONTROLLER
		//ARTISTS DON'T NEED IT
		
		if(kind==1) 
			return new BackUserController();
		else
			return new BackAdminController();
		
	}

}
