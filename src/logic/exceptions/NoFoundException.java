package logic.exceptions;

public class NoFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoFoundException(String message) {
		super(message);
	}
}
