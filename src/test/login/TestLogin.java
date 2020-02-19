package test.login;
/*
 * Author: Marco Ferri
 */

import org.junit.Test;
import static org.junit.Assert.*;

import logic.login.LoginController;
import logic.exceptions.LoginEmptyFieldException;
import logic.bean.GeneralUserBean;

public class TestLogin {
	
	@Test
	public void testUsernameEmptyLogin() {
		
		LoginController lc;
		String output;
		String attended;
		String username;
		String password;
		GeneralUserBean gb;
		
		gb=new GeneralUserBean();
		lc=new LoginController();
		output="";
		attended="Username necessary";
		username="";
		password="password";
		gb.setPassword(password);
		gb.setUsername(username);
		
		
		try {
			lc.login(gb);
		}catch(LoginEmptyFieldException e) {
			output=e.getMessage();
		}finally {
			assertEquals(attended,output);
		}
	}
}
