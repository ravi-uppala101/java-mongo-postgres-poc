package com.capitalone.mongopgres.constants;
/**
 * constants class
 * @author nft887
 *
 */
public class MongoPgConstants {

	public static String DATABASE = "database";
	public static String OPERATION = "operation";
	public static String NUMBER_OF_RECORDS = "numberOfRecords";
	public static String NUMBER_OF_THREADS = "numberOfThreads";
	public static int MIN_RECORDS_PER_THREAD = 0;
	public static int MAX_RECORDS_PER_THREAD = 0;
	public static int RECORDS_PER_THREAD = 0;
	public static final String MONGO_DB = "Mongo";
	public static final String INSERT_SQL = "INSERT INTO PG.CUSTOMER VALUES(?,?,?,?)";
	public static final String UPDATE_SQL = "UPDATE PG.CUSTOMER SET SORT_ID=?,REWARDS_BALANCE=?,ELIGIBILITY_IND=? WHERE ACCOUNT_ID = ?";
	public static final String DELETE_SQL = "DELETE FROM PG.CUSTOMER WHERE ACCOUNT_ID = ?";
}
