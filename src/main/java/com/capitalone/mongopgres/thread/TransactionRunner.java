package com.capitalone.mongopgres.thread;

import java.sql.Connection;
import java.sql.SQLException;
import org.bson.Document;
import org.postgresql.PGConnection;

import com.capitalone.mongopgres.connections.MongoConnection;
import com.capitalone.mongopgres.connections.PostGresConnection;
import com.capitalone.mongopgres.constants.MongoPgConstants;
import com.capitalone.mongopgres.controller.DBController;
import com.mongodb.client.MongoCollection;
/**
 * this is the thread class will be initized by the client class
 * @author nft887
 *
 */
public class TransactionRunner extends Thread {

	DBController controller = new DBController();
	PostGresConnection pgconn = new PostGresConnection();
	MongoConnection mongoconn = new MongoConnection();
	int minRecords = 0;
	int maxRecords = 0;
	int threadNumber = 0;
	public static int totalRecordsCount = 0;
	@Override
	public void run() {
		
		switch(MongoPgConstants.OPERATION){
		
		case "insert"		:		executeInsert(minRecords, maxRecords);
									break;
		
		case "delete"		:		executeDelete();
									break;
		
		case "update"		:		executeUpdate();
									break;
		
		case "select"		:		executeSelect();
									break;
		
		default				: 		invalidOperation();
									break;
		}
	}
	

	public TransactionRunner(int minRecords, int maxRecords, int threadNumber) {
		this.minRecords = minRecords;
		this.maxRecords = maxRecords;
		this.threadNumber = threadNumber;
	}


	public void executeInsert(int minRecords,int maxRecords){
		int countRecordsPerThread = 0;
		if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
			MongoCollection<Document> coll = mongoconn.getConnection();
			for(int i = minRecords; i <= maxRecords; i++){
				controller.insertRecords(countRecordsPerThread,threadNumber,coll,null);
				countRecordsPerThread++;
			}
		}else{
		Connection conn = pgconn.getConnection();
		for(int i = minRecords; i <= maxRecords; i++){
			controller.insertRecords(countRecordsPerThread,threadNumber,null,conn);
			countRecordsPerThread++;
		}
		releasePGConnection(conn);
		}
		calculateCount(countRecordsPerThread);
	}
	public void executeUpdate(){
		int countRecordsPerThread = 0;
		if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
			MongoCollection<Document> coll = mongoconn.getConnection();
			for(int i = minRecords; i <= maxRecords; i++){
				controller.updateRecords(countRecordsPerThread, threadNumber, coll, null);
				countRecordsPerThread++;
			}
		}else{
		Connection conn = pgconn.getConnection();
		for(int i = minRecords; i <= maxRecords; i++){
			controller.updateRecords(countRecordsPerThread,threadNumber,null,conn);
			countRecordsPerThread++;
		}
		releasePGConnection(conn);
		}
		calculateCount(countRecordsPerThread);
		
	}
	public void executeDelete(){
		int countRecordsPerThread = 0;
		if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
			MongoCollection<Document> coll = mongoconn.getConnection();
			for(int i = minRecords; i <= maxRecords; i++){
				controller.deleteRecords(countRecordsPerThread, threadNumber, coll, null);
				countRecordsPerThread++;
			}
		}else{
		Connection conn = pgconn.getConnection();
		for(int i = minRecords; i <= maxRecords; i++){
			controller.deleteRecords(countRecordsPerThread,threadNumber,null,conn);
			countRecordsPerThread++;
		}
		releasePGConnection(conn);
		}
		calculateCount(countRecordsPerThread);
	}
	public void executeSelect(){}
	public void invalidOperation(){}
	
	/**
	 * calculate total records processed count
	 * @param subCount
	 */
	public void calculateCount(int countRecordsPerThread){
		totalRecordsCount = totalRecordsCount + countRecordsPerThread;
	}
	
	/**
	 * releasing the connection
	 * @param conn
	 */
	public void releasePGConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
