package logic.addnews;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.dao.NewsDao;
import logic.exceptions.FieldTooLongException;
import logic.readnews.ReadNewsController;
import logic.bean.NewsBean;

public class AddNewsController {
	private static final Logger logger = Logger.getLogger(AddNewsController.class.getName());
	
	public boolean addNews(NewsBean nb) throws FieldTooLongException {
		NewsDao nd = new NewsDao();
		
		//Time of the postedNews
		LocalDateTime current = LocalDateTime.now();
		
		if (nb.getText().length() >= 200) {
			throw new FieldTooLongException("News body can't be longer than 200 characters");
		}
		return nd.addNews(nb.getText(), nb.getPicturePath(), nb.getArtistId(), current); 
	}
	
	public List<NewsBean> getNews(){
		ReadNewsController rnc = new ReadNewsController();
		return rnc.getNewsAdmin();
	}
	
	public void acceptNews(NewsBean nb) {
		NewsDao nd = new NewsDao();
		nd.manageNews(nb.getId(), NewsDao.ACCEPT);
	}
	
	public void rejectNews(NewsBean nb) {
		Path path = Paths.get(System.getProperty("user.home") + File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "newsPictures" + File.separator + nb.getPicturePath());
		try {
			if(!nb.getPicturePath().equals("")) {
				Files.delete(path);
			}
		} catch (IOException e) {
			logger.log(Level.WARNING, e.toString());
		} finally {
			NewsDao nd = new NewsDao();
			nd.manageNews(nb.getId(), NewsDao.REJECT);
		}
	}
}
