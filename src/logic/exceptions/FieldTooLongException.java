package logic.exceptions;

public class FieldTooLongException extends Exception {

	private static final long serialVersionUID = 2266244222873580808L;

	public FieldTooLongException(String message) {
		super(message);
	}
	
}
