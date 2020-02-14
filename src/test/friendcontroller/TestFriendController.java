package test.friendcontroller;

import org.junit.Test;
import static org.junit.Assert.*;

import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.friends.FriendsController;

public class TestFriendController {
	@Test
	public void testIsFriend() {
		//This test tests if "testusername1" and "testusername2" are friends
		
		FriendsController fc = new FriendsController();
		GeneralUserBean gu = new GeneralUserBean();
		gu.setUsername("testusername1");
		
		UserBean ub = new UserBean();
		ub.setUsername("testusername2");
		
		boolean result = fc.isFriend(gu, ub);
		
		assertEquals(false, result);
	}
}
