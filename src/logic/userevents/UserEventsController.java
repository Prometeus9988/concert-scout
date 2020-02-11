package logic.userevents;

import java.util.List;

import logic.bean.MusicEventBean;
import logic.dao.MusicEventDao;
import logic.entity.MusicEvent;
import logic.utils.Controller;

public class UserEventsController extends Controller{

	public List<MusicEventBean> getUserEvents(String username) {
		MusicEventDao med = new MusicEventDao();
		List<MusicEvent> l = med.getUserEvents(username);
		return this.convertMusicEventList(l);
	}

}
