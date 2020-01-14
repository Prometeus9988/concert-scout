package logic.buyticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.utils.DBConnection;
import logic.utils.Queries;

public class MusicEventDao {
	
	private static final Logger logger = Logger.getLogger(MusicEventDao.class.getName());

	public List<MusicEvent> getSuggestedEvents(String username){
        Statement stmt = null;
        Connection conn = null;
        List<MusicEvent> l = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
            ResultSet rs = Queries.selectSuggestedMusicEvents(stmt, username);
            
            if (!rs.first()) // rs not empty
                return Collections.emptyList();
            
            do{
            	String name = rs.getString("name");
            	String location = rs.getString("location");
            	String bandName = rs.getString("band_name");
            	l.add(new MusicEvent("", bandName, name, "", location));
            } while (rs.next());

        } catch (SQLException se) {
        	// Errore durante l'apertura della connessione
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            	logger.log(Level.WARNING, se2.toString());
            }
        }
        
        return l;
	}
}
