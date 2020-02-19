package test.addnews;
/*
 * Author: Lorenzo Valeriani
 */
import org.junit.Test;

import logic.addnews.AddNewsController;
import logic.bean.NewsBean;
import logic.exceptions.FieldTooLongException;

public class TestAddNewsController {

	private static final String TEST = "test";

	// it is safe to use this annotation because exception can be thrown only at line 35
	@Test(expected = FieldTooLongException.class)
	public void testAddNewsTooLong() throws FieldTooLongException {
		AddNewsController nc = new AddNewsController();

		// create a String with 200 chars
		StringBuilder bld = new StringBuilder();
		for (int i = 0; i < 200; i++) {
			bld.append("a");
		}

		// build NewsBean
		NewsBean nb = new NewsBean();
		nb.setId(42);
		nb.setArtistId(TEST);
		nb.setBandName(TEST);
		nb.setPicturePath(TEST);
		nb.setPostedSince(TEST);
		nb.setProfilePath(TEST);
		nb.setText(bld.toString());

		// call method
		nc.addNews(nb);
	}

}
