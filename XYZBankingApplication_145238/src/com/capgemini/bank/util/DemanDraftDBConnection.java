package com.capgemini.bank.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.bank.exception.DemandDraftException;

public class DemanDraftDBConnection {
	
	private static Properties properties= new Properties();
	private static Connection connection;

	public static Connection getConnection() throws DemandDraftException{
		
		try {
			InputStream inputStream =new FileInputStream("resources/dbDemandDraft.properties");
			properties.load(inputStream);
			String url=properties.getProperty("url");
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
			inputStream.close();
			
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (FileNotFoundException e2) {
			throw new DemandDraftException("Could not Find properties file to connect to database.");
		} catch (IOException e) {
			throw new DemandDraftException("Could not read the database details from properties file.");
		} catch (SQLException e) {
			throw new DemandDraftException("Database connection problem.");
		}
		
		return connection;
	
	}

}
