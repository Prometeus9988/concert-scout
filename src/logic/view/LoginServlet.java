package logic.view;

import java.io.IOException;
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
import logic.bean.UserBean;
import logic.login.LoginController;
import logic.utils.*;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 102831973239L;
	
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
//		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

		LoginController controller = ControllerCreator.getInstance().getLoginController();
		
		String email = "";
		String username = "";
		String password = "";
		String userType = "";

		if(request.getParameter("login") != null) {
			username = request.getParameter("username");
			password = request.getParameter("password");
			GeneralUserBean gu = new GeneralUserBean();
			gu.setUsername(username);
			gu.setPassword(password);
			gu = controller.login(gu);
			
			if(gu == null) {
				rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("login", "notSuccessfull");
			} else if(gu.getRole().equals("user")){
				rd = request.getRequestDispatcher("BuyTicketServlet");
				session.setAttribute("user", gu);
			} else if(gu.getRole().equals("artist")) {
				rd = request.getRequestDispatcher("artistHome.jsp");
				session.setAttribute("user", gu);
			} else if(gu.getRole().equals("admin")) {
				rd = request.getRequestDispatcher("adminMusicEvent.jsp");
				session.setAttribute("user", gu);
			}
		} else if(request.getParameter("register") != null) {
			
				Boolean regResult = false;
				email = request.getParameter("createEmail");
				username = request.getParameter("createUsername");
				password = request.getParameter("createPassword");
				userType = request.getParameter("userType");
				
				System.out.println("aaaaaa " + username + userType);
				
				if(userType.equals("User")){
					String firstName = request.getParameter("firstName");
					String lastName = request.getParameter("lastName");
					UserBean u = new UserBean(username, password, firstName, lastName, email);
					regResult = controller.createUser(u);
				} else if(userType.equals("Artist")){
					String bandName = request.getParameter("bandName");	
					ArtistBean a = new ArtistBean(username, password, bandName, "", email);
					regResult = controller.createArtist(a);
				}
				
				if(regResult){
					rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("reg", "registered");
				} else {
					rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("reg", "notRegistered");
				}
			
		}


		rd.forward(request, response);

	}
	
}
