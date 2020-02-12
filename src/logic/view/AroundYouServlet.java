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

import logic.bean.MusicEventBean;
import logic.buyticket.BuyTicketController;


@WebServlet("/AroundYouServlet")
public class AroundYouServlet extends HttpServlet{

	private static final Logger logger = Logger.getLogger(AroundYouServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	private static final int MAXDISTANCE = 100;
	private static final int MINDISTANCE = 1;
	private static final int DEFAULTRADIUS = 50;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("aroundyou.jsp");
		BuyTicketController btc = new BuyTicketController();
		session.setAttribute("origin", "AroundYouServlet");
		double latitude = 0;
		double longitude = 0;
		
		//get coordinate from the page
		try {
			latitude = Double.parseDouble(request.getParameter("latitude"));
			longitude = Double.parseDouble(request.getParameter("longitude"));
		} catch (Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
		
		int radius = DEFAULTRADIUS;
		
		String radiusString = request.getParameter("slider");
		
		if(radiusString != null) {
			try {
			radius = Integer.parseInt(radiusString);
			} catch (Exception e) {
				logger.log(Level.WARNING, e.toString());
			}
			request.setAttribute("DEFAULTRADIUS", radiusString);
		} else {
			request.setAttribute("DEFAULTRADIUS", DEFAULTRADIUS + "");
		}
		
		List<MusicEventBean> l = btc.getAroundYou(latitude, longitude, radius);
		
		request.setAttribute("MAXDISTANCE", MAXDISTANCE + "");
		request.setAttribute("MINDISTANCE", MINDISTANCE + "");
		
		session.setAttribute("musicEventList", l);
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}
