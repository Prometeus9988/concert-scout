package logic.dao;

import java.sql.SQLException;

public interface DaoAction <T> {
	T act() throws SQLException, ClassNotFoundException;
}
