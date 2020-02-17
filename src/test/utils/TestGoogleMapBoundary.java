package test.utils;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.exceptions.LocationNotFoundException;
import logic.utils.GoogleMapBoundary;

public class TestGoogleMapBoundary {
	
	@Test
	public void testLocateAddress() {
		List<Double> coordinates = new ArrayList<>();
		List<Double> target = new ArrayList<>();
		
		//Coordinates of Piazza di Spagna
		target.add(41.9056798);
		target.add(12.4822062);
		
		try {
			coordinates = GoogleMapBoundary.locateAddress("Piazza di Spagna, Roma, Italia");
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
		
		assertEquals(target, coordinates);
	}
	
	@Test
	public void testLocateAddressFail() {

		boolean isFalse = false;
		
		try {
			GoogleMapBoundary.locateAddress("aaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (IOException | ParseException e) {
			fail("Should not have thrown this exception");
		} catch (LocationNotFoundException e) {
			isFalse = true;
		}
		
		
		assertEquals(true, isFalse);
	}
}
