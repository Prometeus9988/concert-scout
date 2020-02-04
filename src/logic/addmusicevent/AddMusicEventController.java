package logic.addmusicevent;

import logic.bean.MusicEventBean;
import logic.dao.MusicEventDao;
import logic.entity.MusicEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

public class AddMusicEventController {
	public boolean addMusicEvent(MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		Date date = null;
		Date currentDate = new Date();
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(meb.getDate());
		} catch (ParseException e) {
			return false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}
		
		if(date.before(currentDate)){
	        return false;
	    }
		return med.addMusicEvent(meb.getName(), meb.getCoverPath(), meb.getLocation(), meb.getArtistId(), date, meb.getTicketone());
	}
	
	public List<MusicEventBean> viewPendingEvents() {
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getPendingMusicEvent();
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new MusicEventBean(l.get(i)));
		}
		
		return lb;
	}
	
	//TODO forse deve essere relativo all'evento musicale
	public void acceptMusicEvent(MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		med.acceptMusicEvent(meb.getId());
	}
	
	public void rejectMusicEvent(MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		med.rejectMusicEvent(meb.getId());
	}
}
