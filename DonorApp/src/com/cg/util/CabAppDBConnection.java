package com.cg.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.exception.CabApplicationException;

import oracle.jdbc.pool.OracleDataSource;
public class CabAppDBConnection{
	private static Connection conn = null;
	private static CabAppDBConnection instance = null;
	private static Properties props = null;
	private static OracleDataSource dataSource = null;
	
	/*************************************************************************************
	 *  - @throws CabApplicationException
	 *  - Private Constructor
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 20/06/2018
	 *  - Desc:Loads the  jdbc.properties file and Driver Class and gets the connection
	 ***************************************************************************************/
	private CabAppDBConnection() throws CabApplicationException {
		try {
			props = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			throw new CabApplicationException(
					" Could not read the database details from properties file ");
		} catch (SQLException e) {
			throw new CabApplicationException(e.getMessage());
		}

	}
	/*****************************************************************
	 *  - Method Name:getInstance() 
	 *  - Input Parameters : 
	 *  - Return Type :CabAppDBConnection instance
	 *  - Throws : DonorException 
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 20/06/2018
	 *  - Description : Singleton and Thread safe class
	 *******************************************************************/
	
	public static CabAppDBConnection getInstance() throws CabApplicationException {
		synchronized (CabAppDBConnection.class) {
			if (instance == null) {
				instance = new CabAppDBConnection();
			}
		}
		return instance;
	}
	
	/*****************************************************************
	 *  - Method Name:getConnection() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance
	 *  - Throws : DonorException 
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 20/06/2018
	 *  - Description :  Returns connection object
	 *******************************************************************/
	public Connection getConnection() throws CabApplicationException {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {
			throw new CabApplicationException(" Database connection problem");
		}
		return conn;
	}
	
	/*****************************************************************
	 *  - Method Name:loadProperties()
	 *  - Input Parameters : 
	 *  - Return Type :Properties object
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 20/06/2018
	 *  - Description : Returns Properties object
	 *******************************************************************/
	
	private Properties loadProperties() throws IOException {

		if (props == null) {
			Properties newProps = new Properties();
			String fileName = "resources/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);

			inputStream.close();

			return newProps;
		} else {
			return props;
		}
	}


	/*****************************************************************
	 *  - Method Name:prepareDataSource() 
	 *  - Input Parameters : 
	 *  - Return Type :OracleDataSource object
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 20/06/2018
	 *  - Description : Returns OracleDataSource object
	 *******************************************************************/
	
	private OracleDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (props != null) {
				String connectionURL = props.getProperty("dburl");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				dataSource = new OracleDataSource();

				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}
}
