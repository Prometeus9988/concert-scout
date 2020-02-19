package test.addmusicevent;
/*
 * Author: Daniele Ferrarelli
 */
import org.junit.Test;
import static org.junit.Assert.*;

import logic.addmusicevent.*;
import logic.bean.MusicEventBean;
import logic.exceptions.DateException;

public class TestAddMusicEventController {
	
	@Test
	public void testAddMusicEventDate() {
		AddMusicEventController controller = new AddMusicEventController();
		String dateString = "1970-01-01";
		boolean hasFailed = false;
		
		MusicEventBean meb = new MusicEventBean();
		meb.setDate(dateString);
		meb.setName("A Concert");
		meb.setLocation("Roma, Italia");
		
		try {
			controller.addMusicEvent(meb);
		} catch (DateException e) {
			hasFailed = true;
		}
		
		assertEquals(true, hasFailed);
	}
	
	
}
