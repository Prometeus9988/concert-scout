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

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.buyticket.BuyTicketController;
import logic.exceptions.NoArtistFoundException;
import logic.exceptions.NoMusicEventFoundException;

@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(BuyTicketServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		BuyTicketController btc = new BuyTicketController();
		List<MusicEventBean> musicEvents = new ArrayList<>();
		List<ArtistBean> artists = new ArrayList<>();
		session.setAttribute("origin", "BuyTicketServlet");
		
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		String username = gu.getUsername();

		try {
			musicEvents = btc.getSuggestedEvents(username);
			request.setAttribute("FoundMusicEvents", "Suggested Events");
		} catch (NoMusicEventFoundException e) {
			request.setAttribute("FoundMusicEvents", e.getMessage());
		}
		
		try {
			artists = btc.getSuggestedArtist(username);
			request.setAttribute("FoundArtists", "Suggested Artists");
		} catch (NoArtistFoundException e) {
			request.setAttribute("FoundArtists", e.getMessage());
		}
		
		session.setAttribute("musicEventList", musicEvents);
		session.setAttribute("artistList", artists);
		
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}

}
