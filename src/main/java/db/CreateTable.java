package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CreateTable {

	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5432/mydb";
	private static final String DB_USER = "myuser";
	private static final String DB_PASSWORD = "mypass";
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	public static void main(String[] argv) {

		try {

			createDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void createDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "CREATE TABLE DBUSERS("
				+ "USER_ID VARCHAR (5) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, "
				+ "CREATED_BY VARCHAR(20) NOT NULL, "
				+ "CREATED_DATE DATE NOT NULL, "
				+ "PRIMARY KEY (USER_ID) "
				+ ")";

		String insert = "INSERT INTO DBUSERS"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) "
				+ "VALUES"
				+ "('1','mkyong','system', "
				+ "to_date('"
				+ getCurrentTimeStamp()
				+ "', 'yyyy/mm/dd hh24:mi:ss'))";

		String select = "SELECT * from public.dbusers";

		String updateTableSQL = "UPDATE DBUSERS"
				+ " SET USERNAME = 'mkyong_new' "
				+ " WHERE USER_ID = '1'";

		String deleteTableSQL = "DELETE from DBUSERS WHERE USER_ID = '1'";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			ResultSet resultSet = statement.executeQuery(select);

			//for other statements:
			//statement.executeUpdate(updateTableSQL);
			//statement.executeUpdate(insert);
			//statement.execute(createTableSQL);
			//statement.execute(deleteTableSQL);

			while (resultSet.next()) {
				System.out.println(resultSet.getString("USERNAME"));
			}

			ResultSet rs = statement.executeQuery(select);

			System.out.println("Select is performed");
			while (rs.next()) {
				System.out.println(rs.getString("USERNAME"));
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());

	}
}
