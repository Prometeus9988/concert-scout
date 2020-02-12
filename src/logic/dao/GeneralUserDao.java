package logic.dao;

import logic.entity.GeneralUser;
import logic.utils.*;
import java.sql.*;

public class GeneralUserDao extends DaoTemplate{

	public GeneralUser findUser(String username, String password) {
		return this.execute(new DaoAction<GeneralUser>() {
			@Override
			public GeneralUser execute() throws ClassNotFoundException, SQLException {
				Connection conn = null;
				GeneralUser u = null;
				conn = DBLoginConnection.getLoginConnection();

				String sql = "call livethemusic.login(?, ?);\r\n";
				try(PreparedStatement stm = conn.prepareStatement(sql)){
					stm.setString(1, username);
					stm.setString(2, password);
					try (ResultSet rs = stm.executeQuery()) {

						if (!rs.first()) // rs not empty
							return null;

						boolean moreThanOne = rs.first() && rs.next();
						assert !moreThanOne;
						rs.first();

						String role = rs.getString("role");
						String usernameLoaded = rs.getString("username");

						if(usernameLoaded.equals(username)) {
							u = new GeneralUser(usernameLoaded, "", role);
						}
					}
				}

				return u;
			}
		});
	}
}
