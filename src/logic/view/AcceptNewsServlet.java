package logic.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.addnews.AddNewsController;
import logic.bean.NewsBean;

@WebServlet("/AcceptNewsServlet")
public class AcceptNewsServlet extends HttpServlet{
	private static final Logger logger = Logger.getLogger(AcceptNewsServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("ReadNewsServlet");
		AddNewsController controller = new AddNewsController();
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			NewsBean nb = new NewsBean();
			nb.setId(id);

			if(request.getParameter("acceptNews") != null) {
				controller.acceptNews(nb);
			} else if(request.getParameter("rejectNews") != null) {
				nb.setPicturePath(request.getParameter("picture"));
				controller.rejectNews(nb);
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}
