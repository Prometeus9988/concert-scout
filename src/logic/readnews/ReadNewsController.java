package logic.readnews;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import logic.bean.GeneralUserBean;
import logic.bean.NewsBean;
import logic.dao.NewsDao;
import logic.entity.News;

public class ReadNewsController {
	public List<NewsBean> getNews(GeneralUserBean gu){
		NewsDao nd = new NewsDao();
		int i;
			
		List<News> l = nd.getNews(gu.getUsername());
		List<NewsBean> lb = new ArrayList<>();
		
		for(i = 0; i < l.size(); i++) {
			NewsBean nb = new NewsBean();
			News n = l.get(i);

			nb.setPostedSince(this.postedSince(n.getDateTime()));
			nb.setText(n.getText());
			nb.setArtistId(n.getArtistId());
			nb.setBandName(n.getBandName());
			nb.setPicturePath(n.getPicturePath());
			nb.setProfilePath(n.getProfilePath());
			nb.setId(n.getId());
			
			lb.add(nb);
		}
		
		return lb;
	}
	
	private String postedSince(LocalDateTime newsTime) {
		LocalDateTime current = LocalDateTime.now();

		long years = newsTime.until( current, ChronoUnit.YEARS );
		if(years > 0) {
			if(years > 1) {
			return years + "years";
			} else return years + "year";
		}
		
		long months = newsTime.until( current, ChronoUnit.MONTHS );
		if(months > 0) {
			if(months > 1) {
			return months + "months";
			} else return months + "month";
		}
		
		long days = newsTime.until( current, ChronoUnit.DAYS );
		if(days > 0) {
			if(days > 1) {
			return days + "days";
			} else return days + "day";
		}
		
		long hours = newsTime.until( current, ChronoUnit.HOURS );
		if(hours > 0) {
			if(hours > 1) {
			return hours + "hours";
			} else return hours + "hour";
		}
		
		long minutes = newsTime.until( current, ChronoUnit.MINUTES );
		if(minutes > 0) {
			if(minutes > 1) {
			return minutes + "minutes";
			} else return minutes + "minute";
		}
		
		long seconds = newsTime.until( current, ChronoUnit.SECONDS );
		if(seconds > 0) {
			if(seconds > 1) {
			return seconds + "seconds";
			} else return seconds + "second";
		}
		
		return "";
	}
}
