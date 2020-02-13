package logic.fxmlview;

public class SearchBarControllerFactory {
	
	private static SearchBarControllerFactory myInstance;
	
	public static SearchBarControllerFactory getInstance() {
		if(myInstance==null) {
			myInstance= new SearchBarControllerFactory(); 
		}
		return myInstance;
	}
	
	public SearchBarController creator(int kind) {
		if(kind==1) {
			return new SearchEvArController();
		}
		else {
			return new SearchUserController();
		}
	}
	
}
