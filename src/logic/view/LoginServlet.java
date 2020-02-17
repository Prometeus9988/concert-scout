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
import logic.utils.FileManager;
import logic.exceptions.*;

@WebServlet("/LoginServlet")
@MultipartConfig
public class LoginServlet extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	private static final String INDEX = "index.jsp";
	private static final String LOGIN = "login";
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher(INDEX);

		LoginController controller = new LoginController();
		
		if(request.getParameter(LOGIN) != null) {
			rd = this.login(request, session, controller);
		} else if(request.getParameter("register") != null) {
			rd = this.register(request, controller);
		}

		try {
			rd.forward(request, response);
		} catch (Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	private RequestDispatcher login(HttpServletRequest request, HttpSession session, LoginController controller) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		GeneralUserBean gu = new GeneralUserBean();
		gu.setUsername(username);
		gu.setPassword(password);
		try {
			gu = controller.login(gu);
			
			if(gu == null) {
				request.setAttribute(LOGIN, "Wrong username or password");
				return request.getRequestDispatcher(INDEX);
			} else if(gu.getRole().equals("user")){
				session.setAttribute("user", gu);
				return request.getRequestDispatcher("BuyTicketServlet");
			} else if(gu.getRole().equals("artist")) {
				session.setAttribute("user", gu);
				return request.getRequestDispatcher("artistHome.jsp");
			} else if(gu.getRole().equals("admin")) {
				session.setAttribute("user", gu);
				return request.getRequestDispatcher("AdminMusicEventServlet");
			}
		}catch(LoginEmptyFieldException e) {
			request.setAttribute(LOGIN, e.getMessage());
		}
		
		return request.getRequestDispatcher(INDEX);
	}
	
	private RequestDispatcher register(HttpServletRequest request, LoginController controller) {
		
		Boolean regResult = false;
		String email = request.getParameter("createEmail");
		String username = request.getParameter("createUsername");
		String password = request.getParameter("createPassword");
		String userType = request.getParameter("userType");
		String newFileName = null;
		String fileName = "";
		Part filePart = null;

		//For profile picture
		try {
			filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		} catch (Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
		
		if(filePart != null) {
			fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		}

		newFileName = FileManager.generateNewFileName(fileName, false, username);
		
		regResult = this.create(userType, username, password, email, newFileName, controller, request);
		

	    //
	    if(Boolean.TRUE.equals(regResult)){
			request.setAttribute("reg", "registered");
			
			//File upload- if registration successfull loads the file in profilePictures
			
		    if(!fileName.equals("") && filePart != null) {
		    	String path = FileManager.PROFILE;
			    File file = new File(path, fileName);
			    File newFile = new File(path, newFileName);
			    try (InputStream input = filePart.getInputStream()) {
			    		Files.copy(input, file.toPath());
			    } catch (Exception e) {
			    	logger.log(Level.WARNING, e.toString());
			    }
			    
			    if(!file.renameTo(newFile)) {
			    	logger.log(Level.WARNING, "Unable to rename: {0}", fileName);
			    }
		    }
		} else {
			request.setAttribute("reg", "notRegistered");
		}
	    return request.getRequestDispatcher(INDEX);
	}
	
	private boolean create(String userType, String username, String password, String email, String newFileName, LoginController controller, HttpServletRequest request) {
		if(userType.equals("User")){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			UserBean u = new UserBean();
			u.setUsername(username);
			u.setPassword(password);
			u.setEmail(email);
			u.setName(firstName);
			u.setSurname(lastName);
			u.setProfilePicture(newFileName);
			return controller.createUser(u);
		} else if(userType.equals("Artist")){
			String bandName = request.getParameter("bandName");
			ArtistBean a = new ArtistBean();
			a.setUsername(username);
			a.setPassword(password);
			a.setBandName(bandName);
			a.setProfilePicture(newFileName);
			a.setEmail(email);
			return controller.createArtist(a);
		}
		
		return false;
	}
	
}
