package test.utils;
/*
 * Author: Marco Ferri
 */
import org.junit.Test;
import static org.junit.Assert.*;

import logic.utils.FileManager;

public class TestFileManager {
	
	@Test
	public void testGenerateNewFileName() {
		
		boolean needSalt;
		String fileName;
		String username;
		String attended;
		String output;
		
		needSalt=false;
		fileName="File";
		username="Name";
		output=FileManager.generateNewFileName(fileName, needSalt, username);
		attended="NameFile";
		
		assertEquals(attended,output);
	}
}
