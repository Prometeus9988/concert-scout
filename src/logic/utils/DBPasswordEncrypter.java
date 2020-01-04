package logic.utils;

public class DBPasswordEncrypter {
	
	private static String pass = "password";
	
	private DBPasswordEncrypter() {
		
	}
	
	public static String getPasswordDB() {
		return pass;
	}
}
