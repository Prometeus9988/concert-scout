package logic.exceptions;
/*
 * Author: Daniele Ferrarelli 
 */
public class NoFriendRequestException extends NoFoundException{

	private static final long serialVersionUID = 734143551798557517L;

	public NoFriendRequestException(String message) {
		super(message);
	}

}
