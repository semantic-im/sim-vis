package jdbc;

import java.sql.*;


public class ViewTable {

	public Statement stmt = null;
	
	public ResultSet viewTable(Connection con, String dbName, String tableName) throws SQLException {

		String query = "select * from " + dbName + "." + tableName;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;

		} catch (SQLException e ) {

		} finally {

		}

		return null;

	}
}