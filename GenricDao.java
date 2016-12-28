package com.neilson.read;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class GenricDao {

	private static final String path = "D:\\Nielsen\\Data\\";
	Connection connectionIMDB = null;
	Statement statement = null;
	
	public GenricDao() {

		try {
			Class.forName("org.sqlite.JDBC");
			connectionIMDB = DriverManager
					.getConnection("jdbc:sqlite:D:\\Nielsen\\Data\\collected.sqlite");
			statement = connectionIMDB.createStatement();
			statement
					.execute("attach database 'D:\\Nielsen\\Data\\AI_6626-12549_67750.sqlite' as 'ai'");
			statement
					.execute("attach database 'D:\\Nielsen\\Data\\IMDB.sqlite' as 'imdb'");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet getResult(final String query) {

		ResultSet rs = null;
		try {
			rs = statement.executeQuery(query);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}

	public Stream<String> getStream(final String fileName) {
		
		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(path+fileName+".csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;

	}

	public void closeConnection() {
		try {
			statement.close();
			connectionIMDB.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}
