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
import logic.bean.UserBean;
import logic.exceptions.NoFriendRequestException;
import logic.exceptions.NoFriendException;
import logic.friends.FriendsController;

@WebServlet("/FriendsServlet")
public class FriendsServlet extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(FriendsServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("friends.jsp");
		FriendsController fc = new FriendsController();
		List<UserBean> friendList = new ArrayList<>();
		List<UserBean> requestList = new ArrayList<>();
		session.setAttribute("origin", "FriendsServlet");
		
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		String username = gu.getUsername();

		try {
			friendList = fc.getFriends(username);
			request.setAttribute("FoundFriends", "Your friends");
		} catch (NoFriendException e) {
			request.setAttribute("FoundFriends", e.getMessage());
		}
		
		request.setAttribute("friendList", friendList);
		
		try {
			requestList = fc.getRequests(username);
			request.setAttribute("FoundRequests", "Your friend requests");
		} catch (NoFriendRequestException e) {
			request.setAttribute("FoundRequests", e.getMessage());
		}
		
		request.setAttribute("requestList", requestList);

		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}

}