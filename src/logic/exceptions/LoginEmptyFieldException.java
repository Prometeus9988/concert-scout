package logic.exceptions;
/*
 * Author: Marco Ferri 
 */
public class LoginEmptyFieldException extends EmptyFieldException {
	private static final long serialVersionUID = -442186624575240316L;

	public LoginEmptyFieldException(String message) {
		super(message);
	}
	
}
