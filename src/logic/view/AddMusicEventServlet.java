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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import logic.addmusicevent.AddMusicEventController;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;

@MultipartConfig

public class AddMusicEventServlet extends HttpServlet{
	private static final Logger logger = Logger.getLogger(AddMusicEventServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("artistHome.jsp");
		AddMusicEventController controller = new AddMusicEventController();
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		String res = null;
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		String date = request.getParameter("date");
		String ticketone = request.getParameter("ticketone");
		String fileName = null;
		String newFileName = null;
		Part filePart = null;
		boolean result = false;
		
		filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		if(filePart != null) {
			fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		}
		
		if(fileName.equals("")) {
			fileName = "";
			newFileName = "";
		} else {
			newFileName = gu.getUsername() + name + fileName;
		}
		
		MusicEventBean meb = new MusicEventBean();
		meb.setArtistId(gu.getUsername());
		meb.setName(name);
		meb.setCoverPath(newFileName);
		meb.setLocation(location);
		meb.setTicketone(ticketone);
		meb.setDate(date);
		
		//TODO check if all the parameters are not null except for the picture wich is optional
			result = controller.addMusicEvent(meb);
			if(result) {
				res = "added";
			} else {
				res = "notAdded";
			}
		
		if(!fileName.equals("") && result){
			String path = System.getProperty("user.home") + File.separator
					+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
					+ "trunk" + File.separator + "WebContent" + File.separator
					+ "img" + File.separator + "concertPictures";
		    File file = new File(path, fileName);
		    File newFile = new File(path, newFileName);
		    try (InputStream input = filePart.getInputStream()) {
		    		Files.copy(input, file.toPath());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    file.renameTo(newFile);
		}
		request.setAttribute("result", res);
		
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}