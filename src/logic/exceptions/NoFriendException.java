package logic.exceptions;
/*
 * Author: Marco Ferri 
 */
public class NoFriendException extends NoFoundException{
	private static final long serialVersionUID = -7341691368037213509L;

	public NoFriendException(String message) {
		super(message);
	}
}
