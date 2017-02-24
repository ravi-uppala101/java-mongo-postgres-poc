package com.capitalone.mongopgres.controller;

import java.sql.Connection;

import org.bson.Document;

import com.capitalone.mongopgres.constants.MongoPgConstants;
import com.capitalone.mongopgres.mongo.service.MongoService;
import com.capitalone.mongopgres.postgres.service.PGDBService;
import com.mongodb.client.MongoCollection;

public class DBController {
	
	PGDBService pgservice = new PGDBService();
	MongoService mongoservice = new MongoService();
	/**
	 * inserting records
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param coll
	 * @param conn
	 */
	public void insertRecords(int countRecordsPerThread, int threadNumber,MongoCollection<Document> coll,Connection conn){
		if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
			mongoservice.insertRecords(countRecordsPerThread,threadNumber,coll);
		}else{
			pgservice.insertRecords(countRecordsPerThread,threadNumber,conn);
		}
	}
	/**
	 * updating records
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param coll
	 * @param conn
	 */
	public void updateRecords(int countRecordsPerThread, int threadNumber,MongoCollection<Document> coll,Connection conn){
		if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
			mongoservice.updateRecords(countRecordsPerThread, threadNumber, coll);
		}else{
			pgservice.updateRecords(countRecordsPerThread,threadNumber,conn);
		}
	}
	/**
	 * deleting records
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param coll
	 * @param conn
	 */
	public void deleteRecords(int countRecordsPerThread, int threadNumber,MongoCollection<Document> coll,Connection conn){
		if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
			mongoservice.deleteRecords(countRecordsPerThread, threadNumber, coll);
		}else{
			pgservice.deleteRecords(countRecordsPerThread,threadNumber,conn);
		}
	}
}
