import java.sql.*;
import java.util.*;

/**
 * @author Case Regan and Jia Kang
 * creates a database named CoinData with two tables, users and transactions
 *
 *
 */
public class Database {
	// change this value to suit your needs
	public static final String PORT_NUMBER = "8889";
	public static Statement statement;


	/**
	 * executes a query with a descriptive print to the command line
	 * @param query - the SQL query that this method will execute
	 * @param statement - the Statement object that will process this query
	 * @throws SQLException
	 */
	public static void executeSQL(String query, Statement statement) throws SQLException {
		System.out.println("Executing query: " + query);
		statement.execute(query);
	}

	/**
	 * executes a query and returns a ResultSet with a descriptive print to the command line
	 * @param query - the SQL query that this method will execute
	 * @param statement - the Statement object that will process this query
	 * @return - the ResultSet returned by said Statement's processing of said query
	 * @throws SQLException
	 */
	public static ResultSet executeSELECT(String query, Statement statement) throws SQLException {
		System.out.println("Executing query: " + query);
		ResultSet result = statement.executeQuery(query);
		return result;
	}


	/**
	 * This method helps to connect to database server
	 */
	public static void connectServer() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", "root", "root");
			System.out.println("Connection made");
			statement = connection.createStatement();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method connects to the server and creates the database with tables implemented
	 */
	public static void createDBAndUse() {
		try {

			connectServer();

			// creates the database if not existing
			executeSQL("CREATE DATABASE IF NOT EXISTS CoinDatabase;", statement);

			// makes sure that all following queries are directed at the created database
			executeSQL("USE CoinDatabase;", statement);

			executeSQL("CREATE TABLE IF NOT EXISTS users ("
					+ "PublicKey int, "
					+ "Balance double, "
					+ "PRIMARY KEY (PublicKey)"
					+ ");",
					statement);

			executeSQL("CREATE TABLE IF NOT EXISTS contacts ("
					+ "UserKey int, "
					+ "friends varchar(50), "
					+ "FOREIGN KEY (UserKey) REFERENCES users(PublicKey)"
					+ ");",
					statement);


			executeSQL("CREATE TABLE IF NOT EXISTS transactions ("

					+ "transactionID int, "
					+ "history varchar(50), "
					+ "PRIMARY KEY (history), "
					+ "FOREIGN KEY (transactionID) REFERENCES users(PublicKey)"
					+ ");",
					statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	/**
	 * This method adds new user to the database after a random user ID is generated
	 * @param userID
	 */
	public static void addUsers(int userID) {
		try{
			connectServer();
			executeSQL("USE CoinDatabase;", statement);
			executeSQL("INSERT INTO users(PublicKey) VALUES ('" + Integer.toString(userID) + "');", statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void addBalance(int UserKey, double CoinBalance) {
		try{
			connectServer();
			executeSQL("USE CoinDatabase;", statement);
			executeSQL("UPDATE users SET Balance = '" + Double.toString(CoinBalance) + "' WHERE PublicKey = '" +
														Integer.toString(UserKey) + "';", statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addTransaction(Transaction t)
	{
		
	}
	public static ArrayList<Transaction> getTransactionHistory()
	{
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("Execution started");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", "root", "root");) {
			System.out.println("Connection made");
			Statement statement = connection.createStatement();


			// creates the database if not existing
			executeSQL("CREATE DATABASE IF NOT EXISTS CoinDatabase;", statement);

			// makes sure that all following queries are directed at the created database
			executeSQL("USE CoinDatabase;", statement);
		}


			// creates a table named users with fields id (the primary key) and the user's friends
//			executeSQL("CREATE TABLE users ("
//					+ "userID varchar(50), "
//					+ "friends varchar(50), "
//					+ "PRIMARY KEY (userID)"
//					+ ");",
//					statement);
//
//
//			// creates a table named transactions with fields id (which is the primary key),
//			// history (which represents the past transactions) which references
//			// the id field of a row in the users table
//			executeSQL("CREATE TABLE transactions ("
//
//					+ "transactionID varchar(50), "
//					+ "history varchar(50), "
//					+ "PRIMARY KEY (transactionID), "
//					+ "FOREIGN KEY (history) REFERENCES users(userID)"
//					+ ");",
//					statement);
//
//			// populates the users table with three hardcoded values
//			executeSQL("INSERT INTO users(userID) VALUES ('case');", statement);
//			executeSQL("INSERT INTO users(userID) VALUES ('jia');", statement);
//			executeSQL("INSERT INTO users(userID) VALUES ('kon');", statement);
//
//			// queries the CoinDatabase to get all id fields from the users table
//			ResultSet userIDs = executeSELECT("SELECT userID FROM users;", statement);
//
//			ArrayList<String> ids = new ArrayList<String>();
//
//			// iterates through the query results and records the values in a pair of ArrayLists to avoid some concurrency problems
//			while(userIDs.next()) {
//
//				String id = userIDs.getString("userID");
//				ids.add(id);
//			}
//
//			// populates the projects table using values based on the queried ones
//			for(int i = 0; i < ids.size(); i++) {
//
//				executeSQL("INSERT INTO transactions VALUES ('" + Integer.toString(i) + "', '" + ids.get(i) +  "');", statement);
//			}

		catch(SQLException e) {
			e.printStackTrace();
		}
//
//			System.out.println("Finished, program exiting");
//
//
	}
}
