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

import logic.addmusicevent.AddMusicEventController;
import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.bean.UserBean;
import logic.buyticket.BuyTicketController;
import logic.followartist.FollowArtistController;
import logic.friends.FriendsController;
import logic.userevents.UserEventsController;

public class ButtonHandler  extends HttpServlet{
	private static final Logger logger = Logger.getLogger(ButtonHandler.class.getName());
	private static final long serialVersionUID = 102831973239L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		BuyTicketController btc = new BuyTicketController();
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		FollowArtistController fac = new FollowArtistController();
		AddMusicEventController amec = new AddMusicEventController();
		FriendsController fc = new FriendsController();
		UserEventsController uc = new UserEventsController();
		if(request.getParameter("m") != null) {
			String id = request.getParameter("Mevent");
			MusicEventBean meb = btc.getMusicEvent(id, gu);
			session.setAttribute("Mevent", meb);
			boolean isPart = btc.isParticipating(gu, meb);
			request.setAttribute("isPart", isPart);
			rd = request.getRequestDispatcher("musicEventDetail.jsp");
		} else if(request.getParameter("a") != null) {
			String username = request.getParameter("artist");
			// TODO is it needed to access DAO again?
			ArtistBean ab = btc.getArtist(username);
			session.setAttribute("artist", ab);
			boolean isFoll = fac.isFollowing(gu, ab);
			request.setAttribute("isFoll", isFoll);
			rd = request.getRequestDispatcher("artistDetail.jsp");
		} else if(request.getParameter("f") != null) {
			UserBean ub = new UserBean();
			ub.setUsername(request.getParameter("f"));
			ub.setName(request.getParameter("name"));
			ub.setSurname(request.getParameter("surname"));
			ub.setProfilePicture(request.getParameter("profileP"));
			// TODO for now keep it here, maybe split buttonhandler in the future
			List <MusicEventBean> targetEvents = uc.getUserEvents(ub.getUsername());
			session.setAttribute("target", ub);
			session.setAttribute("targetEvents", targetEvents);
			boolean isFriend = fc.isFriend(gu, ub);
			request.setAttribute("isFriend", isFriend);
			String who = fc.whoSentRequest(gu, ub);
			request.setAttribute("request", who);
			rd = request.getRequestDispatcher("userDetail.jsp");
		} else if(request.getParameter("friend") != null) {
			UserBean ub = new UserBean();
			ub.setUsername(request.getParameter("target"));
			boolean isFriend = fc.isFriend(gu, ub);
			String who;
			if (isFriend) {
				fc.unfriend(gu, ub);
			} else if ((who = fc.whoSentRequest(gu, ub)).equals("none")) {
				fc.requestFriend(gu, ub);
			} else if (who.equals("user")) {
				fc.removeRequest(gu, ub);
			} else {
				fc.acceptRequest(gu, ub);
			}
			request.setAttribute("request", fc.whoSentRequest(gu, ub));
			request.setAttribute("isFriend", fc.isFriend(gu, ub));
			rd = request.getRequestDispatcher("userDetail.jsp");
		} else if (request.getParameter("decline") != null) {
			UserBean ub = new UserBean();
			ub.setUsername(request.getParameter("target"));
			fc.declineRequest(gu, ub);
			request.setAttribute("request", fc.whoSentRequest(gu, ub));
			request.setAttribute("isFriend", fc.isFriend(gu, ub));
			rd = request.getRequestDispatcher("userDetail.jsp");
		} else if(request.getParameter("addPart") != null) {
			MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
			boolean isPart = btc.isParticipating(gu, meb);
			if(isPart){
				btc.removeParticipation(gu, meb);
			} else {
				btc.addParticipation(gu, meb);
			}
			request.setAttribute("isPart", !isPart);
			rd = request.getRequestDispatcher("musicEventDetail.jsp");
		} else if(request.getParameter("back") != null) {
			String origin = (String) session.getAttribute("origin");
			String searchString = (String) session.getAttribute("searchString");
			request.setAttribute("searchString", searchString);
			rd = request.getRequestDispatcher(origin);
		} else if(request.getParameter("follow") != null) {
			ArtistBean ab = (ArtistBean) session.getAttribute("artist");
			boolean isFoll = fac.isFollowing(gu, ab);
			request.setAttribute("isFoll", !isFoll);
			if(isFoll){
				fac.unfollow(gu, ab);
			} else {
				fac.follow(gu, ab);
			}
			rd = request.getRequestDispatcher("artistDetail.jsp");
		} else if(request.getParameter("accept") != null) {
			MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
			amec.acceptMusicEvent(meb);
			rd = request.getRequestDispatcher("AdminMusicEventServlet");
		} else if(request.getParameter("reject") != null) {
			MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
			amec.rejectMusicEvent(meb);
			rd = request.getRequestDispatcher("AdminMusicEventServlet");
		} else if(request.getParameter("goToTicketone") != null) {
			MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
			rd = request.getRequestDispatcher(meb.getTicketone());
			response.sendRedirect(meb.getTicketone());
		}

		try {
			rd.forward(request, response);
		}catch(IllegalStateException e) {
			logger.log(Level.INFO, "redirected");
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	private RequestDispatcher gotoTicketone(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
		response.sendRedirect(meb.getTicketone());
		return request.getRequestDispatcher(meb.getTicketone());
	}
	
}

