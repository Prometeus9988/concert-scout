package logic.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DaoTemplate {
	
    private static final Logger logger = Logger.getLogger("Dao");
	
	protected <T> T execute (DaoAction <T> da) {
		try {
			return da.execute();
		} catch (SQLException se) {
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());	
        }
		return null;
	}

}
