package logic.buyticket;

import java.util.ArrayList;
import java.util.List;

public class ArtistDao {
	public List<Artist> getSuggestedArtistStub(String username){
		List<Artist> artist = new ArrayList<>();
		artist.add(new Artist("9495", "Iron Maiden", "Default_Path"));
		artist.add(new Artist("1345", "Ozzy Osbourne", "Default_Path"));
		artist.add(new Artist("1433", "Locust", "Default_Path"));
		return artist;
	}
}
