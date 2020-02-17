package test.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

import logic.bean.MusicEventBean;
import logic.buyticket.BuyTicketController;
import logic.entity.MusicEvent;
import logic.utils.Controller;
import logic.bean.ArtistBean;
import logic.entity.Artist;

public class TestController {
	
	private static final String TEST = "test";

	@Test
	public void testMusicEventBeanConvert() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		List<Double> coordinates = new ArrayList<>();
		coordinates.add(0.0);
		coordinates.add(0.0);

		// Build ad-hoc MusicEventBean
		MusicEventBean meb1 = new MusicEventBean();
		meb1.setId(42);
		meb1.setArtistId(TEST);
		meb1.setName(TEST);
		meb1.setCoverPath(TEST);
		meb1.setLocation(TEST);
		meb1.setBandName(TEST);
		meb1.setTicketone(TEST);
		meb1.setLatitude(coordinates.get(0));
		meb1.setLongitude(coordinates.get(1));

		// Build MusicEvent
		MusicEvent me = new MusicEvent(42, TEST, TEST, TEST, TEST, TEST, TEST);
		me.setCoordinates(coordinates);

		// Get method through reflection and call it
		Method method = Controller.class.getDeclaredMethod("convert", MusicEvent.class);
		method.setAccessible(true);
		MusicEventBean meb2 = (MusicEventBean)method.invoke(new BuyTicketController(), me);

		assertTrue(EqualsBuilder.reflectionEquals(meb1, meb2));
	}
	
	@Test
	public void artistBeanConverter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		ArtistBean attended=new ArtistBean();
		//init ArtistBean
		attended.setUsername(TEST);
		attended.setBandName(TEST);
		attended.setProfilePicture(TEST);
		
		Artist ar=new Artist(TEST,TEST,TEST);
		
		Method method=Controller.class.getDeclaredMethod("convert", Artist.class);
		method.setAccessible(true);
		ArtistBean output;
		output=(ArtistBean)method.invoke(new BuyTicketController(), ar);
		assertTrue(EqualsBuilder.reflectionEquals(attended,output));
	}

}
