package logic.dao;

import java.sql.SQLException;

public interface DaoAction <T> {
	T execute() throws SQLException, ClassNotFoundException;
}
