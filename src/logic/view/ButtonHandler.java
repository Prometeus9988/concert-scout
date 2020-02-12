package logic.view;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.buyticket.BuyTicketController;
import logic.followartist.FollowArtistController;

public class ButtonHandler  extends HttpServlet{
	private static final Logger logger = Logger.getLogger(ButtonHandler.class.getName());
	private static final long serialVersionUID = 102831973239L;

	private static final String ARTIST = "artist";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");

		FollowArtistController fac = new FollowArtistController();
		
		if(request.getParameter("m") != null) {
			//A music event is selected
			rd = this.gotoMusicEvent(gu, session, request);
		} else if(request.getParameter("a") != null) {
			rd = this.gotoArtistProfile(gu, session, request);
		} else if(request.getParameter("back") != null) {
			String origin = (String) session.getAttribute("origin");
			String searchString = (String) session.getAttribute("searchString");
			request.setAttribute("searchString", searchString);
			rd = request.getRequestDispatcher(origin);
		} else if(request.getParameter("follow") != null) {
			ArtistBean ab = (ArtistBean) session.getAttribute(ARTIST);
			boolean isFoll = fac.isFollowing(gu, ab);
			request.setAttribute("isFoll", !isFoll);
			if(isFoll){
				fac.unfollow(gu, ab);
			} else {
				fac.follow(gu, ab);
			}
			rd = request.getRequestDispatcher("artistDetail.jsp");
		}

		try {
			rd.forward(request, response);
		}catch(IllegalStateException e) {
			logger.log(Level.INFO, "redirected");
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}

	private RequestDispatcher gotoMusicEvent(GeneralUserBean gu, HttpSession session, HttpServletRequest request) {
		BuyTicketController controller = new BuyTicketController();
		int index = Integer.parseInt(request.getParameter("index"));
		List<?> mlist = (List<?>) session.getAttribute("musicEventList");
		MusicEventBean meb = (MusicEventBean) mlist.get(index);
		session.setAttribute("Mevent", meb);
		boolean isPart = controller.isParticipating(gu, meb);
		session.setAttribute("isPart", isPart);
		return request.getRequestDispatcher("musicEventDetail.jsp");
	}
	
	private RequestDispatcher gotoArtistProfile(GeneralUserBean gu, HttpSession session, HttpServletRequest request) {
		BuyTicketController btc = new BuyTicketController();
		FollowArtistController fac = new FollowArtistController();
		
		String username = request.getParameter(ARTIST);
		
		List<?> artistList = (List<?>) session.getAttribute("artistList");
		
		ArtistBean ab = this.checkArtistPresence(artistList, username);
		
		if(ab == null) {
		 ab = btc.getArtist(username);
		}
		
		session.setAttribute(ARTIST, ab);
		boolean isFoll = fac.isFollowing(gu, ab);
		request.setAttribute("isFoll", isFoll);
		return request.getRequestDispatcher("artistDetail.jsp");
	}
	
	//This method check if an Artist is present in the session before accessing the Data Base
	private ArtistBean checkArtistPresence(List<?> l, String username) {
		for(int i = 0; i < l.size(); i++) {
			ArtistBean ab = (ArtistBean) l.get(i);
			if(ab.getUsername().equals(username)){
				return ab;
			}
		}
		return null;
	}
	
}

