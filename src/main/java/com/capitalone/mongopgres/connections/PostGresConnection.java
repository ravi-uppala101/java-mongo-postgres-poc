package com.capitalone.mongopgres.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * this class returns postgres connection
 * @author nft887
 *
 */
public class PostGresConnection {
	Connection con = null;
	String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "Passw0rd";
	public Connection getConnection(){
		try {
	         con = DriverManager.getConnection(url, user, password);
		}catch(SQLException sqle){
			}
		return con;
}
	
}