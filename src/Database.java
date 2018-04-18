/**
 * This class is the database storing all user public keys, account balance and their transaction history,
 * will update balance automatically whenever a transaction gets verified
 */

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
	 * This method helps to connect to database server for set up
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
	 * Contact lists data use is put in icebox for this implementation
	 */
	public static void createDBAndUse() {
		try {

			connectServer();

			// creates the database if not existing
			executeSQL("CREATE DATABASE IF NOT EXISTS CoinDatabase;", statement);

			// makes sure that all following queries are directed at the created database
			executeSQL("USE CoinDatabase;", statement);
			
			// creates the tables if not existing
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
					+ "PublicKey int, "					
					+ "history varchar(50), "
					+ "PRIMARY KEY (history) "
					+ ");",
					statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	/**
	 * This method adds new user to the database after its public key is generated
	 * @param userID
	 */
	public static void addUsers(int userID) {
		try{
			connectServer();
			executeSQL("USE CoinDatabase;", statement);
			executeSQL("INSERT INTO users(PublicKey) VALUES ('" + Integer.toString(userID) + "');", statement);
			//executeSQL("INSERT INTO transactions(PublicKey) VALUES ('" + Integer.toString(userID) + "');", statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method updates the account balance of the user whenever a transaction gets verified
	 * @param UserKey
	 * @param CoinBalance
	 */
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

	/**
	 * This method uploads transaction data to the database with involved users
	 * @param UserKey
	 * @param history
	 */
	public static void addTransaction(int UserKey, String transaction) {
		try{
			connectServer();
			executeSQL("USE CoinDatabase;", statement);
			executeSQL("INSERT INTO transactions VALUES ('" + Integer.toString(UserKey) + 
											"', '" + transaction +  "');", statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns an ArrayList containing all verified transaction history 
	 * @param UserKey
	 * @return
	 */
	public static ArrayList<String> getTransactionHistory(int UserKey){
		try{
			connectServer();
			executeSQL("USE CoinDatabase;", statement);
			ResultSet rset = executeSELECT("SELECT history, PublicKey FROM transactions;", statement);
			ArrayList<String> transactionHistory = new ArrayList<String>();
			while(rset.next()) {
				String transaction = rset.getString("history");
				int PublicKey = rset.getInt("PublicKey");
				if (PublicKey == UserKey) {
					transactionHistory.add(transaction);
				}
				
			}
			return transactionHistory;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
