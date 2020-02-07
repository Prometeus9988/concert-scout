package logic.userevents;

import java.util.ArrayList;
import java.util.List;

import logic.bean.MusicEventBean;
import logic.dao.MusicEventDao;
import logic.entity.MusicEvent;

public class UserEventsController {

	public List<MusicEventBean> getUserEvents(String username) {
		MusicEventDao med = new MusicEventDao();
		List<MusicEvent> l = med.getUserEvents(username);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			MusicEvent me = l.get(i);
			MusicEventBean meb = new MusicEventBean();
			meb.setId(me.getId());
			meb.setArtistId(me.getArtistId());
			meb.setName(me.getName());
			meb.setCoverPath(me.getCoverPath());
			meb.setLocation(me.getLocation());
			meb.setBandName(me.getBandName());
			meb.setTicketone(me.getTicketone());
			lb.add(meb);
		}
		
		return lb;
	}

}
