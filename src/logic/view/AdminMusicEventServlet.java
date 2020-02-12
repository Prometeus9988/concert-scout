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
import logic.bean.MusicEventBean;

public class AdminMusicEventServlet extends HttpServlet{
	private static final Logger logger = Logger.getLogger(AdminMusicEventServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("adminMusicEvent.jsp");
		AddMusicEventController amc = new AddMusicEventController();
		session.setAttribute("origin", "AdminMusicEventServlet");
		
		List<MusicEventBean> musicEvents = amc.viewPendingEvents();

		session.setAttribute("musicEventList", musicEvents);
		
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
		
	}
}
