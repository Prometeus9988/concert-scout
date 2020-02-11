package logic.addmusicevent;

import logic.bean.MusicEventBean;
import logic.dao.MusicEventDao;
import logic.entity.MusicEvent;
import logic.utils.Controller;
import logic.utils.GoogleMapBoundary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

public class AddMusicEventController extends Controller{
	public boolean addMusicEvent(MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		Date date = null;
		Date currentDate = new Date();
		List<Double> coordinates = null;
		
		try {
			coordinates = GoogleMapBoundary.locateAddress(meb.getLocation());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException pe) {
			pe.printStackTrace();
		}

		if (meb.getDate() != null) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(meb.getDate());
			} catch (ParseException e) {
				return false;
			}
		} else {
			return false;
		}
		
		if(date.before(currentDate)){
	        return false;
	    }
		return med.addMusicEvent(meb.getName(), meb.getCoverPath(), meb.getLocation(), meb.getArtistId(), date, meb.getTicketone(), coordinates);
	}
	
	public List<MusicEventBean> viewPendingEvents() {
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getPendingMusicEvents();
		return this.convertMusicEventList(l);
	}
	
	//TODO forse deve essere relativo all'evento musicale
	public void acceptMusicEvent(MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		med.acceptMusicEvent(meb.getId());
	}
	
	public void rejectMusicEvent(MusicEventBean meb) {
		Path path = Paths.get(System.getProperty("user.home") + File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "concertPictures" + File.separator + meb.getCoverPath());
		try {
			if(!meb.getCoverPath().equals("concert.jpg")) {
				Files.delete(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MusicEventDao med = new MusicEventDao();
			med.rejectMusicEvent(meb.getId());
		}
	}
}
