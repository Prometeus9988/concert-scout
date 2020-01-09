package logic.buyticket;

import java.util.ArrayList;
import java.util.List;

public class MusicEventDao {
	public List<MusicEvent> getSuggestedEventsStub(String username){
		List<MusicEvent> musicEvents = new ArrayList<>();
		musicEvents.add(new MusicEvent("3425", "Iron Maiden", "Black Tour", "coverPath", "Rome"));
		musicEvents.add(new MusicEvent("6456", "Black Sabbath", "Perry Mason Tour", "coverPath", "Frosinone"));
		musicEvents.add(new MusicEvent("6456", "Black Sabbath", "Perry Mason Tour", "coverPath", "Frosinone"));
		musicEvents.add(new MusicEvent("6456", "Black Sabbath", "Perry Mason Tour", "coverPath", "Frosinone"));
		musicEvents.add(new MusicEvent("6456", "Black Sabbath", "Perry Mason Tour", "coverPath", "Frosinone"));
		return musicEvents;
	}
}
