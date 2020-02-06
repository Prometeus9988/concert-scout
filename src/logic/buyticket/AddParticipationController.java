package logic.buyticket;

import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.dao.MusicEventDao;

public class AddParticipationController {
	public void addParticipation(GeneralUserBean user, MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		med.addParticipation(user.getUsername(), meb.getId());
	}
	
	public void removeParticipation(GeneralUserBean user, MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		med.removeParticipation(user.getUsername(), meb.getId());
	}
	
	public boolean isParticipating(GeneralUserBean user, MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		return med.isParticipating(user.getUsername(), meb.getId());
	}
}
