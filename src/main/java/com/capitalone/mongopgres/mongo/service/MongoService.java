package com.capitalone.mongopgres.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.capitalone.mongopgres.connections.MongoConnection;
import com.capitalone.mongopgres.mongo.model.Customer;
import com.capitalone.mongopgres.utils.ConverterUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
/**
 * mongo service class to porform operation with mongodb
 * @author nft887
 *
 */
public class MongoService {

	Customer customer;
	Customer updCustomer;
	Customer findCustomer;
	MongoConnection conn = new MongoConnection();
	
	/**
	 * insertRecords method to insert the document into the colleciton
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param coll
	 */
	public void insertRecords(int countRecordsPerThread, int threadNumber,MongoCollection<Document> coll){
		setCustomer(countRecordsPerThread, threadNumber,"1111","9999","Y");
		coll.insertOne(ConverterUtils.JavaToJsonConverter(customer));
	}
	/**
	 * updateRecords method to update the document into the collection
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param coll
	 */
	public void updateRecords(int countRecordsPerThread, int threadNumber,MongoCollection<Document> coll){
		Document findDoc = new Document();
		String accId = ConverterUtils.getAccount(countRecordsPerThread, threadNumber);
		setUpdCustomer(countRecordsPerThread, threadNumber,accId,"3333","5555","N");
		findDoc.put("accountId",  accId);
		coll.findOneAndUpdate(findDoc, new Document("$set", ConverterUtils.JavaToJsonConverter(updCustomer)));
	}
	
	public void deleteRecords(int countRecordsPerThread, int threadNumber,MongoCollection<Document> coll){
		Document findDoc = new Document();
		String accId = ConverterUtils.getAccount(countRecordsPerThread, threadNumber);
		findDoc.put("accountId",  accId);
		coll.findOneAndDelete(findDoc);
	}
	
	/**
	 * setting the customer to insert a record
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param sortId
	 * @param rewardsBalance
	 * @param eligibilityIndicator
	 */
	public void setCustomer(int countRecordsPerThread,int threadNumber,String sortId, String rewardsBalance, String eligibilityIndicator){
		customer = new Customer();
		customer.setAccountId(ConverterUtils.getAccount(countRecordsPerThread, threadNumber));
		customer.setSortId(sortId);
		customer.setRewardsBalance(rewardsBalance);
		customer.setEligibilityIndicator(eligibilityIndicator);
	}
	/**
	 * find customer with account id
	 * @param countRecordsPerThread
	 * @param threadNumber
	 */
	public void findCustomerByAccId(int countRecordsPerThread,int threadNumber){
		findCustomer = new Customer();
		findCustomer.setAccountId(ConverterUtils.getAccount(countRecordsPerThread, threadNumber));
	}
	/**
	 * setting update customer object
	 * @param countRecordsPerThread
	 * @param threadNumber
	 * @param accId
	 * @param sortId
	 * @param rewardsBalance
	 * @param eligibilityIndicator
	 */
	public void setUpdCustomer(int countRecordsPerThread,int threadNumber,String accId,String sortId, String rewardsBalance, String eligibilityIndicator){
		updCustomer = new Customer();
		updCustomer.setAccountId(accId);
		updCustomer.setSortId(sortId);
		updCustomer.setRewardsBalance(rewardsBalance);
		updCustomer.setEligibilityIndicator(eligibilityIndicator);
	}
}
