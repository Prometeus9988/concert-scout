package logic.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.bean.UserBean;
import logic.exceptions.NoMusicEventFoundException;
import logic.friends.FriendsController;

@WebServlet("/FriendButtonServlet")
public class FriendButtonServlet  extends HttpServlet{
	private static final Logger logger = Logger.getLogger(FriendButtonServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;

	private static final String TARGET = "target";
	private static final String ISFRIEND = "isFriend";
	private static final String REQUEST = "request";
	private static final String USERDETAIL = "userDetail.jsp";
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		FriendsController fc = new FriendsController();

		if(request.getParameter("f") != null) {

			UserBean ub = new UserBean();
			ub.setUsername(request.getParameter("f"));
			ub.setName(request.getParameter("name"));
			ub.setSurname(request.getParameter("surname"));
			ub.setProfilePicture(request.getParameter("profileP"));
			session.setAttribute(TARGET, ub);
			List <MusicEventBean> targetEvents = new ArrayList<>();

			try {
				targetEvents = fc.getUserEvents(ub.getUsername());
				session.setAttribute("foundEvents", ub.getUsername() + " is going to:");
			} catch (NoMusicEventFoundException nme) {
				session.setAttribute("foundEvents", nme.getMessage());
			}
			session.setAttribute("musicEventList", targetEvents);
			boolean isFriend = fc.isFriend(gu, ub);
			request.setAttribute(ISFRIEND, isFriend);
			String who = fc.whoSentRequest(gu, ub);
			request.setAttribute(REQUEST, who);
			rd = request.getRequestDispatcher(USERDETAIL);

		} else if(request.getParameter("friend") != null) {

			UserBean ub = new UserBean();
			ub.setUsername(request.getParameter(TARGET));
			boolean isFriend = fc.isFriend(gu, ub);
			String who = fc.whoSentRequest(gu, ub);
			if (isFriend) {
				fc.unfriend(gu, ub);
			} else if (who.equals("none")) {
				fc.requestFriend(gu, ub);
			} else if (who.equals("user")) {
				fc.removeRequest(gu, ub);
			} else {
				fc.acceptRequest(gu, ub);
			}
			request.setAttribute(REQUEST, fc.whoSentRequest(gu, ub));
			request.setAttribute(ISFRIEND, fc.isFriend(gu, ub));
			rd = request.getRequestDispatcher(USERDETAIL);

		} else if (request.getParameter("decline") != null) {

			UserBean ub = new UserBean();
			ub.setUsername(request.getParameter(TARGET));
			fc.declineRequest(gu, ub);
			request.setAttribute(REQUEST, fc.whoSentRequest(gu, ub));
			request.setAttribute(ISFRIEND, fc.isFriend(gu, ub));
			rd = request.getRequestDispatcher(USERDETAIL);
		}

		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}

}
