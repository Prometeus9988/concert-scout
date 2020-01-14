package logic.buyticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.login.GeneralUserDao;
import logic.utils.DBConnection;
import logic.utils.Queries;

public class ArtistDao {
	
	private static final Logger logger = Logger.getLogger(GeneralUserDao.class.getName());
	
	public List<Artist> getSuggestedArtistStub(String username){
		List<Artist> artist = new ArrayList<>();
		artist.add(new Artist("IronMaiden", "Iron Maiden", "Default_Path"));
		artist.add(new Artist("OzzyOsbourne", "Ozzy Osbourne", "Default_Path"));
		artist.add(new Artist("Locust", "Locust", "Default_Path"));
		return artist;
	}
	
	public List<Artist> getSuggestedArtist(String username){
        Statement stmt = null;
        Connection conn = null;
        List<Artist> l = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
            ResultSet rs = Queries.selectSuggestedArtist(stmt, username);
            
            if (!rs.first()) // rs not empty
                return null;
            
            do{
            	String usernameA = rs.getString("username");
            	String bandName = rs.getString("band_name");
            	String profilePicture = rs.getString("profile_picture_path");
 
            	l.add(new Artist(usernameA, bandName, profilePicture));
            } while (rs.next());

        } catch (SQLException se) {
        	// Errore durante l'apertura della connessione
        	logger.log(Level.WARNING, se.toString());
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
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
