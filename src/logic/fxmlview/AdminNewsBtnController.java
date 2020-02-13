package logic.fxmlview;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import logic.bean.NewsBean;
import logic.addnews.*;

public class AdminNewsBtnController {
	
	@FXML
	private Button acpBtn;
	@FXML
	private Button rejBtn;
	
	private NewsBean myNews;
	private AdminGraphicChange agc;
	private AddNewsController anc;
	
	@FXML
	public void acceptNews(ActionEvent ev) {
		anc.acceptNews(this.myNews);
		this.agc.toNews(this.acpBtn.getScene());
	}
	
	@FXML
	public void rejectNews(ActionEvent ev) {
		anc.rejectNews(this.myNews);
		this.agc.toNews(this.acpBtn.getScene());
	}
	
	public void init(NewsBean nb) {
		//INIT controller
		this.myNews=nb;
		this.agc=AdminGraphicChange.getInstance();
		this.anc=new AddNewsController();
	}
	
}
