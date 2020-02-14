package logic.fxmlview;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import logic.utils.GoogleMapBoundary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class MapController {

    @FXML
    private Label latitudeLabel;

    @FXML
    private Label longitudeLabel;

    @FXML
    private GoogleMapView googleMapView;
    
    public void init(double latitude,double longitude) {
    	googleMapView.setKey(GoogleMapBoundary.getAPI());
    	googleMapView.addMapInializedListener(() -> configureMap(latitude,longitude));
        
    }

    protected void configureMap(double latitude,double longitude) {
        MapOptions mapOptions = new MapOptions();
        
        //Position to insert
        LatLong location = new LatLong(latitude,longitude);
        
        //Focus on location
        mapOptions.center(location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(9);
        GoogleMap map = googleMapView.createMap(mapOptions, false);

        //Create marker of the position
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(location);
        Marker marker = new Marker(markerOptions1);
        
        map.addMarker(marker);

    }
}
