package logic.buyticket;

import java.util.ArrayList;
import java.util.List;

public class ArtistDao {
	public List<Artist> getSuggestedArtistStub(String username){
		List<Artist> artist = new ArrayList<>();
		artist.add(new Artist("IronMaiden", "Iron Maiden", "Default_Path"));
		artist.add(new Artist("OzzyOsbourne", "Ozzy Osbourne", "Default_Path"));
		artist.add(new Artist("Locust", "Locust", "Default_Path"));
		return artist;
	}
}
