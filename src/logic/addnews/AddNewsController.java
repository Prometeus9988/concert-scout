package logic.addnews;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import logic.dao.NewsDao;
import logic.readnews.ReadNewsController;
import logic.bean.NewsBean;

public class AddNewsController {
	public boolean addNews(NewsBean nb) {
		NewsDao nd = new NewsDao();
		
		//Time of the postedNews
		LocalDateTime current = LocalDateTime.now();
		
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
			e.printStackTrace();
		} finally {
			NewsDao nd = new NewsDao();
			nd.manageNews(nb.getId(), NewsDao.REJECT);
		}
	}
}
