package logic.login;

import logic.utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
	
    private static String pass = "password";
    private static String user = "default";
    private static String dbUrl = "jdbc:mysql://localhost/livethemusic";
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    private UserDao() {

    	//Non so se funziona ma evita un code smell
    }
    
    public static User findUser(String username, String password) {
    	// STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        User u = null;
        try {


            conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
//            String sql = "SELECT name, surname, username, password FROM user where username = '"
//                    + username + "' AND password = '" + password + "';";
//            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs = Queries.selectUserLogin(stmt, username, password);

            if (!rs.first()) // rs not empty
                return null;

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione

            rs.first();

            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String usernameLoaded = rs.getString("username");

            if(usernameLoaded.equals(username)) {
            	u = new User(usernameLoaded, "", name, surname);
            }
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            DBConnection.closeConnection();

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
        	logger.log(Level.WARNING, se.toString());
        } catch (Exception e) {
            // Errore nel loading del driver
            logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
            	logger.log(Level.WARNING, se.toString());
            }
        }
        return u;
    }
    
}
