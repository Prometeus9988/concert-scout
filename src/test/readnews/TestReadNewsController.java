package test.readnews;
/*
 * Author: Lorenzo Valeriani
 */
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import logic.readnews.ReadNewsController;

public class TestReadNewsController {
	
	@Test
	public void testGetYears() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// set LocalDateTime variables for test
		LocalDateTime aDateTime = LocalDateTime.of(2020, Month.FEBRUARY, 17, 23, 00, 40);
		LocalDateTime anotherDateTime = LocalDateTime.of(1998, Month.OCTOBER, 06, 12, 30, 40);

		Method method = ReadNewsController.class.getDeclaredMethod("getYears", LocalDateTime.class, LocalDateTime.class);
		method.setAccessible(true);
		String time = (String)method.invoke(new ReadNewsController(), aDateTime, anotherDateTime);
		
		assertEquals("21years", time);
	}

}
