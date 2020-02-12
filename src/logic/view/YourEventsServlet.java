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
import logic.bean.MusicEventBean;
import logic.userevents.UserEventsController;

@WebServlet("/YourEventsServlet")
public class YourEventsServlet extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(YourEventsServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("yourEvents.jsp");
		UserEventsController ec = new UserEventsController();
		session.setAttribute("origin", "YourEventsServlet");
		
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		String username = gu.getUsername();

		List<MusicEventBean> musicEventList = ec.getUserEvents(username);
		session.setAttribute("musicEventList", musicEventList);

		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}
