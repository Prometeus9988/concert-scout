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
import javax.servlet.http.HttpSession;

import logic.addmusicevent.AddMusicEventController;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.buyticket.BuyTicketController;

@WebServlet("/MusicEventServlet")
public class MusicEventServlet  extends HttpServlet{
		private static final Logger logger = Logger.getLogger(MusicEventServlet.class.getName());
		private static final long serialVersionUID = 102831973239L;
		private static final String EVENT = "Mevent";
		
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			HttpSession session = request.getSession();
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			boolean isPart;
			GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
			BuyTicketController btc = new BuyTicketController();
			AddMusicEventController amec = new AddMusicEventController();
			
			if(request.getParameter("addPart") != null) {
				MusicEventBean meb = (MusicEventBean) session.getAttribute(EVENT);
				isPart = btc.isParticipating(gu, meb);
				if(isPart){
					btc.removeParticipation(gu, meb);
				} else {
					btc.addParticipation(gu, meb);
				}
				session.setAttribute("isPart", !isPart);
				
				rd = request.getRequestDispatcher("musicEventDetail.jsp");
			} else if(request.getParameter("accept") != null) {
				MusicEventBean meb = (MusicEventBean) session.getAttribute(EVENT);
				amec.acceptMusicEvent(meb);
				rd = request.getRequestDispatcher("AdminMusicEventServlet");
			} else if(request.getParameter("reject") != null) {
				MusicEventBean meb = (MusicEventBean) session.getAttribute(EVENT);
				amec.rejectMusicEvent(meb);
				rd = request.getRequestDispatcher("AdminMusicEventServlet");
			} else if(request.getParameter("goToTicketone") != null) {
				MusicEventBean meb = (MusicEventBean) session.getAttribute(EVENT);
				rd = request.getRequestDispatcher(meb.getTicketone());
				try {
					response.sendRedirect(meb.getTicketone());
				} catch (Exception e) {
					logger.log(Level.INFO, "Invalid link " + meb.getTicketone());
				}
			}
			
			try {
				rd.forward(request, response);
			}catch(IllegalStateException e) {
				logger.log(Level.INFO, "redirected");
			} catch(Exception e) {
				logger.log(Level.WARNING, e.toString());
			}
		}
}
