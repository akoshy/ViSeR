package com.search;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionDB {
	private Connection databaseConnection;
	public Statement connectJDBC() {
		Statement executeStatement = null;
		try {
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3306/ReRanking";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			databaseConnection = DriverManager.getConnection(url, userName, password);
			executeStatement = databaseConnection.createStatement();
		} catch (Exception e) {
			System.out.println("Connection couldn't be made");
			//e.printStackTrace();
		}
		return executeStatement;
	}

}
