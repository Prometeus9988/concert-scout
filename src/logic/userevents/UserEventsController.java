package logic.userevents;

import java.util.List;

import logic.bean.MusicEventBean;
import logic.dao.MusicEventDao;
import logic.entity.MusicEvent;
import logic.exceptions.NoMusicEventFoundException;
import logic.utils.Controller;

public class UserEventsController extends Controller{

	public List<MusicEventBean> getUserEvents(String username) throws NoMusicEventFoundException {
		MusicEventDao med = new MusicEventDao();
		List<MusicEvent> l = med.getUserEvents(username);
		if (l.isEmpty()) {
			throw new NoMusicEventFoundException("You are not attending any event");
		}
		return this.convertMusicEventList(l);
	}

}
