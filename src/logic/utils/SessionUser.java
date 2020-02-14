package logic.utils;

import logic.bean.*;

public class SessionUser {
	
	private GeneralUserBean userSession=null;
	private static SessionUser myInstance=null;
	
	private SessionUser() {
		
	}
	
	public static SessionUser getInstance() {
		if(myInstance==null) {
			myInstance=new SessionUser();
		}
		return myInstance;
	} 
	
	
	public void setSession(GeneralUserBean userSession) {
		
		if(this.userSession==null) {
			this.userSession=userSession;
		}
		
	}
	
	public GeneralUserBean getSession() {
		return this.userSession;
	}
	
	
	public void closeSession() {
		this.userSession=null;
	}
	
	
}
