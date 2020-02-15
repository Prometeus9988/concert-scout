package logic.fxmlview;

import logic.bean.MusicEventBean;

public class AdminEvDetController extends EvDetailsController {

	@Override
	public void init(MusicEventBean myEvent, String from, String searchString){

		AdminGraphicChange agc = AdminGraphicChange.getInstance();

		//INIT MENU BAR
		agc.menuBar(this.menuBar, from);

		//INIT LABEL NAMES
		initLabelNames(myEvent);

		//INIT BACK BUTTON
		agc.backButton(this.backButton, from, searchString);

		//INIT ROLE BUTTONS
		agc.evDetailsButtons(this.buttons, myEvent);

		//INIT IMAGE
		initImage(myEvent);

		//INIT SCROLL AND MAP
		initScroll();
		agc.addMap(map, myEvent.getLatitude(), myEvent.getLongitude());
		
	}

}