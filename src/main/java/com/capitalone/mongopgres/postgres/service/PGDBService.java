package com.capitalone.mongopgres.postgres.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capitalone.mongopgres.connections.PostGresConnection;
import com.capitalone.mongopgres.constants.MongoPgConstants;
import com.capitalone.mongopgres.postgres.model.Customer;
import com.capitalone.mongopgres.utils.ConverterUtils;

/**
 * postgres db service class to perform the operations with postgres
 * @author nft887
 *
 */
public class PGDBService {
	PostGresConnection connection = new PostGresConnection();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Customer customer;
    Customer updCustomer;
	public void insertRecords(int countRecordsPerThread, int threadNumber,Connection conn){
		try {
			pst = conn.prepareStatement(MongoPgConstants.INSERT_SQL);
			setCustomer(countRecordsPerThread, threadNumber);
			pst.setInt(1, customer.getAccountId());
			pst.setInt(2, customer.getSortId());
			pst.setInt(3, customer.getRewardsBalance());
			pst.setString(4, customer.getEligibilityIndicator());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateRecords(int countRecordsPerThread, int threadNumber,Connection conn){
		try {
			pst = conn.prepareStatement(MongoPgConstants.UPDATE_SQL);
			setUpdCustomer(countRecordsPerThread, threadNumber);
			pst.setInt(1, updCustomer.getSortId());
			pst.setInt(2, updCustomer.getRewardsBalance());
			pst.setString(3, updCustomer.getEligibilityIndicator());
			pst.setInt(4, Integer.parseInt(ConverterUtils.getAccount(countRecordsPerThread, threadNumber)));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRecords(int countRecordsPerThread, int threadNumber,Connection conn){
		try {
			pst = conn.prepareStatement(MongoPgConstants.DELETE_SQL);
			pst.setInt(1, Integer.parseInt(ConverterUtils.getAccount(countRecordsPerThread, threadNumber)));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setCustomer(int countRecordsPerThread,int threadNumber){
		customer = new Customer();
		customer.setAccountId(Integer.parseInt(ConverterUtils.getAccount(countRecordsPerThread, threadNumber)));
		customer.setSortId(1111);
		customer.setRewardsBalance(9000);
		customer.setEligibilityIndicator("Y");
	}
	public void setUpdCustomer(int countRecordsPerThread,int threadNumber){
		updCustomer = new Customer();
		updCustomer.setSortId(1919);
		updCustomer.setRewardsBalance(1919);
		updCustomer.setEligibilityIndicator("N");
	}
}
