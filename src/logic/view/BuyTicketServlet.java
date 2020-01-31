package logic.view;

import java.io.IOException;
import java.util.List;
import java.util.Random;

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
import logic.utils.*;


@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet{
	
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		BuyTicketController btc = ControllerCreator.getInstance().getBuyTicketController();
		int i;
		
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		String username = gu.getUsername();
		
		List<MusicEventBean> musicEvents = btc.getSuggestedEvents(username);
		List<ArtistBean> artists = btc.getSuggestedArtist(username);
		request.setAttribute("musicEventList", musicEvents);
		request.setAttribute("artistList", artists);
		
		if(request.getParameter("search") != null){
			String searchString = request.getParameter("search");
			request.setAttribute("searchString", searchString);
		    request.setAttribute("musicEventList", btc.getSearchMusicEvent(searchString));
			request.setAttribute("artistList", btc.getSearchArtist(searchString));
			rd = request.getRequestDispatcher("searchResult.jsp");
		} else if(request.getParameter("addPart") != null) {
			MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
			boolean isPart = btc.isParticipating(gu, meb);
			request.setAttribute("isPart", isPart);
			if(isPart){
				btc.removeParticipation(gu, meb);
			} else {
				btc.addParticipation(gu, meb);
			}
			rd = request.getRequestDispatcher("musicEventDetail.jsp");
			
		} else {
			for(i = 0; i < musicEvents.size(); i++){
				if(request.getParameter("m" + i)!=null){
					session.setAttribute("Mevent", musicEvents.get(i));
					boolean isPart = btc.isParticipating(gu, musicEvents.get(i));
					request.setAttribute("isPart", isPart);
					rd = request.getRequestDispatcher("musicEventDetail.jsp");
				}
				if(request.getParameter("ar" + i)!=null){
					List<ArtistBean> sArt=btc.getSearchArtist(musicEvents.get(i).getArtistId());
					request.setAttribute("artist",sArt.get(0));
					rd = request.getRequestDispatcher("artistDetail.jsp");
				}
			}
			
			for(i = 0; i < artists.size(); i++){
				if(request.getParameter("a" + i)!=null){
					request.setAttribute("artist",artists.get(i));
					rd = request.getRequestDispatcher("artistDetail.jsp");
				}
			}
		}
		
		rd.forward(request, response);
	}
	
}
