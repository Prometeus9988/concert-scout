package logic.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.login.LoginController;
import logic.utils.*;


@WebServlet("/LoginServlet")
@MultipartConfig
public class LoginServlet extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	private static final String INDEX = "index.jsp";
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher(INDEX);

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
				rd = request.getRequestDispatcher(INDEX);
				request.setAttribute("login", "notSuccessfull");
			} else if(gu.getRole().equals("user")){
				rd = request.getRequestDispatcher("BuyTicketServlet");
				session.setAttribute("user", gu);
			} else if(gu.getRole().equals("artist")) {
				rd = request.getRequestDispatcher("artistHome.jsp");
				session.setAttribute("user", gu);
			} else if(gu.getRole().equals("admin")) {
				rd = request.getRequestDispatcher("AdminMusicEventServlet");
				session.setAttribute("user", gu);
			}
		} else if(request.getParameter("register") != null) {
			
				Boolean regResult = false;
				email = request.getParameter("createEmail");
				username = request.getParameter("createUsername");
				password = request.getParameter("createPassword");
				userType = request.getParameter("userType");
				String newFileName = null;
				String fileName = null;
				Part filePart = null;

				//For profile picture
				filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
				fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				if(fileName == null) {
					fileName = "";
					newFileName = "";
				} else {
					newFileName = username + fileName;
				}
				
				if(userType.equals("User")){
					String firstName = request.getParameter("firstName");
					String lastName = request.getParameter("lastName");
					
					//TODO set userbean to support profile picture
					UserBean u = new UserBean(username, password, email, firstName, lastName, "");
					u.setProfilePicture(fileName);
					regResult = controller.createUser(u);
				} else if(userType.equals("Artist")){
					String bandName = request.getParameter("bandName");
					ArtistBean a = new ArtistBean(username, password, bandName, newFileName, email);
					regResult = controller.createArtist(a);
				}
				

			    //
			    if(Boolean.TRUE.equals(regResult)){
					rd = request.getRequestDispatcher(INDEX);
					request.setAttribute("reg", "registered");
					
					//File upload- if registration successfull loads the file in profilePictures
					
				    if(!fileName.equals("")) {
				    	String path = System.getProperty("user.home") + File.separator
								+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
								+ "trunk" + File.separator + "WebContent" + File.separator
								+ "img" + File.separator + "profilePictures";
					    File file = new File(path, fileName);
					    File newFile = new File(path, newFileName);
					    try (InputStream input = filePart.getInputStream()) {
					    		Files.copy(input, file.toPath());
					    } catch (Exception e) {
					    	e.printStackTrace();
					    }
					    file.renameTo(newFile);
				    }
				} else {
					rd = request.getRequestDispatcher(INDEX);
					request.setAttribute("reg", "notRegistered");
				}
			
		}

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
}
