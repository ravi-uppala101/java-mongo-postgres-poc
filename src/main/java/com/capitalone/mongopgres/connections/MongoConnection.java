package com.capitalone.mongopgres.connections;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;

/**
 * this class returns mongocollection
 * @author nft887
 *
 */
public class MongoConnection {

	public MongoCollection<Document> getConnection(){
		@SuppressWarnings("resource")
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase mongodb = client.getDatabase("Rewards");
		MongoCollection<Document> coll = mongodb.getCollection("poc");
		return coll;
	}
}
