package logic.fxmlview;

import logic.bean.MusicEventBean;

public class UserEvDetController extends EvDetailsController {

	@Override
	public void init(MusicEventBean myEvent, String from, String searchString){

		UserGraphicChange ucg=UserGraphicChange.getInstance();

		//INIT MENU BAR
		ucg.menuBar(this.menuBar, from);

		//INIT LABEL NAMES
		initLabelNames(myEvent);

		//INIT BACK BUTTON
		ucg.backButton(this.backButton, from, searchString);

		//INIT ROLE BUTTONS
		ucg.evDetailsButtons(this.buttons, myEvent);

		//INIT IMAGE
		initImage(myEvent);

		//INIT SCROLL AND MAP
		initScroll();
		ucg.addMap(map, myEvent.getLatitude(), myEvent.getLongitude());

	}

}
