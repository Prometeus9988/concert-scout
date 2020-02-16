package logic.exceptions;

public class EmptyFieldException extends Exception {
	
	private static final long serialVersionUID = -5045983312099226651L;

	public EmptyFieldException(String message) {
		super(message);
	}
}
