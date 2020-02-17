package logic.utils;

import java.io.File;

public class FileManager {
	
	private static final String PROJECT = System.getProperty("user.home") + File.separator
			+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
			+ "trunk" + File.separator + "WebContent" + File.separator
			+ "img" + File.separator;

	public static final String PROFILE = PROJECT + "profilePictures";

	public static final String NEWS = PROJECT + "newsPictures";

	public static final String EVENT = PROJECT + "concertPictures";

	private FileManager() {
		
	}
	
	public static String generateNewFileName(String fileName, boolean needSalt, String username) {
		String newFileName = "";
		if(!fileName.equals("")) {
			newFileName = username; 
			if(needSalt) {
				newFileName = newFileName + RandomNumberGenerator.getInstance().randomInt();
			}
			newFileName = newFileName + fileName;
		}

		return newFileName;
	}

}
