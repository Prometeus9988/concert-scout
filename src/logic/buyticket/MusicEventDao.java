package logic.buyticket;

import java.util.ArrayList;

public class MusicEventDao {
	public ArrayList<MusicEvent> getSuggestedEventsStub(String username){
		ArrayList<MusicEvent> musicEvents = new ArrayList<MusicEvent>();
		musicEvents.add(new MusicEvent("3425", "Iron Maiden", "Black Tour", "coverPath", "Rome"));
		musicEvents.add(new MusicEvent("6456", "Black Sabbath", "Perry Mason Tour", "coverPath", "Frosinone"));
		return musicEvents;
	}
}
