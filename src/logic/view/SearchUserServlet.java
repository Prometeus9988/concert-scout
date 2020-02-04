package logic.view;

import java.io.IOException;
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
import logic.friends.FriendsController;

@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet{
	private static final Logger logger = Logger.getLogger(SearchUserServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		// TODO rework controllerCreator logic
		FriendsController fc = new FriendsController();
		String ss = "searchString";

		List<UserBean> users = null;
		session.setAttribute("origin", "SearchUserServlet");
		
		String searchString = request.getParameter(ss);
		
		if(searchString == null) {
			searchString = (String) request.getAttribute(ss);
		}
		
		request.setAttribute(ss, searchString);
		
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");

		users = fc.getSearchUser(searchString, gu.getUsername());
		
		session.setAttribute(ss, searchString);
		request.setAttribute("userList", users);
		rd = request.getRequestDispatcher("searchUserResult.jsp");
		
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}
