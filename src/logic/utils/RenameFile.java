package logic.utils;

public class RenameFile {
	
	private RenameFile() {
		
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
