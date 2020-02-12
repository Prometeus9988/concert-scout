package logic.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;

public class GoogleMapBoundary {
	
	private static final String GOOGLEAPI = "AIzaSyCwCl0AlDzqfQOwJQcaok76MR-T8KWQgDw";
	
	private GoogleMapBoundary(){
		
	}
	
	public static List<Double> locateAddress(String address) throws IOException, ParseException{
		String jsonString = "";
		List<Double> coordinates = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        
		//Request to the geocoding service
		URL geocodingUrl = new URL("https://maps.googleapis.com/maps/api/geocode/json?"
        		+ "address=" + address.replace(" ", "+") + "&key=" + GOOGLEAPI);
        URLConnection geocoding = geocodingUrl.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                		geocoding.getInputStream()));
        String inputLine;

        //Create a JSON string from the response
        while ((inputLine = in.readLine()) != null) 
            str.append("\n" + inputLine);
        in.close();
        
        jsonString = str.toString();
        JSONParser parser = new JSONParser();
        
        //Get coordinates from JSON string
        JSONObject object = (JSONObject) parser.parse(jsonString);
        JSONArray resultsArray = (JSONArray) object.get("results");
        JSONObject results = (JSONObject) resultsArray.get(0);
        JSONObject geometry = (JSONObject) results.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        
        double lat = (double) location.get("lat"); 
        double lng = (double) location.get("lng");
           
        coordinates.add(lat);
        coordinates.add(lng);
        
        return coordinates;
	}

	public static String getAPI() {
		return GoogleMapBoundary.GOOGLEAPI;
	}
}
