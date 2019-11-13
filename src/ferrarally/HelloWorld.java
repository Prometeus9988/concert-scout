package ferrarally;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorld {
	private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

	public static void main(String[] args) {
		logger.log(Level.INFO, "Hello World!");
	}
}
