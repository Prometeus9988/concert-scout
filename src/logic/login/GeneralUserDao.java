package logic.login;

import logic.utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralUserDao {
	

    private static final Logger logger = Logger.getLogger(GeneralUserDao.class.getName());

    private GeneralUserDao() {

    }
    
    public static GeneralUser findUser(String username, String password) {
        Statement stmt = null;
        Connection conn = null;
        GeneralUser u = null;
        try {
            conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
            ResultSet rs = Queries.selectGeneralUserLogin(stmt, username, password);

            if (!rs.first()) // rs not empty
                return null;

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione

            rs.first();

            String role = rs.getString("role");
            String usernameLoaded = rs.getString("username");

            if(usernameLoaded.equals(username)) {
            	u = new GeneralUser(usernameLoaded, "", role);
            }
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();

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
        }
        //System.out.println("Found " + u.getRole() + "with username" + u.getUsername());
        return u;
    }
}
