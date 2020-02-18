package logic.followartist;

import logic.bean.GeneralUserBean;

import java.util.List;

import logic.bean.ArtistBean;
import logic.dao.ArtistDao;
import logic.dao.UserDao;
import logic.entity.Artist;
import logic.exceptions.NoArtistFoundException;
import logic.utils.Controller;

public class FollowArtistController extends Controller{
	public boolean isFollowing(GeneralUserBean user, ArtistBean ab) {
		UserDao ud = new UserDao();
		return ud.isFollowing(user.getUsername(), ab.getUsername());
	}

	public void follow(GeneralUserBean gu, ArtistBean ab) {
		UserDao ud = new UserDao();
		ud.follow(gu.getUsername(), ab.getUsername());
	} 
	
	public void unfollow(GeneralUserBean gu, ArtistBean ab) {
		UserDao ud = new UserDao();
		ud.unfollow(gu.getUsername(), ab.getUsername());
	} 
	
	
	public List<ArtistBean> getSearchArtist(String searchString) throws NoArtistFoundException {
		ArtistDao ad = new ArtistDao();

		List<Artist> l = ad.getSearchArtist(searchString);
		if(l.isEmpty()) {
			throw new NoArtistFoundException("No artists found");
		}
		return this.convertArtistList(l);
	}
	
	public List<ArtistBean> getSuggestedArtist(String username) throws NoArtistFoundException{
		ArtistDao ad = new ArtistDao();
		List<Artist> l = ad.getSuggestedArtist(username);
		if(l.isEmpty()) {
			throw new NoArtistFoundException("No suggested artists found");
		}
		return this.convertArtistList(l);
	}
	
	
	public ArtistBean getArtist(String username) {
		ArtistDao ad = new ArtistDao();
		Artist a = ad.getArtist(username);
		return this.convert(a);
	}
}
