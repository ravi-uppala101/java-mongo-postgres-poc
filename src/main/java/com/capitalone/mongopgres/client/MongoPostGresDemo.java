package com.capitalone.mongopgres.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.capitalone.mongopgres.constants.MongoPgConstants;
import com.capitalone.mongopgres.thread.TransactionRunner;
/**
 * This is the client class which asks series of questions to process the records against the database
 * @author Ratnakar Luchmapurkar
 *
 */
public class MongoPostGresDemo {
	static final int SECONDS_PER_MINUTE = 60;
	static final int MINUTES_PER_HOUR = 60;
	public static void main(String[] args) throws IOException{
		
		// -- database name to communicate with backend database
		System.out.println("Please enter the database name you want to connect (PostGres/Mongo)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MongoPgConstants.DATABASE = br.readLine();
		
		// -- database operation name to perform 
		System.out.println("Please enter the operation which you want to perform (INSERT/UPDATE/DELETE/FINDALL)");
		br = new BufferedReader(new InputStreamReader(System.in));
		MongoPgConstants.OPERATION = br.readLine();
		
		// -- number of records to process
		System.out.println("Please enter number records to process");
		br = new BufferedReader(new InputStreamReader(System.in));
		MongoPgConstants.NUMBER_OF_RECORDS = br.readLine();
		
		// -- number of threads to spawn to process the records
		System.out.println("Please enter number of threads to spwan");
		br = new BufferedReader(new InputStreamReader(System.in));
		MongoPgConstants.NUMBER_OF_THREADS = br.readLine();
		
		
		// -- initializing the threads
		Thread[] threadArray = new Thread[Integer.parseInt(MongoPgConstants.NUMBER_OF_THREADS)];
		
		// -- spawnning threads
		int i = 0;
		int min_records_per_thread = 0;
		int max_records_per_thread = 0;
		int records_per_thread = 0;
		while(threadArray.length > i){
			if(i == 0){
				records_per_thread = Integer.parseInt(MongoPgConstants.NUMBER_OF_RECORDS) / Integer.parseInt(MongoPgConstants.NUMBER_OF_THREADS);
			}
			max_records_per_thread = (min_records_per_thread - 1) + records_per_thread;
			threadArray[i] = new TransactionRunner(min_records_per_thread,max_records_per_thread,i);
			threadArray[i].start();
			min_records_per_thread = max_records_per_thread + 1;
			i++;
		}
		displayResults(threadArray);
	}
	
	/**
	 * displaying the results after all threads are completed the job
	 * @param threads
	 */
	public static void displayResults(Thread[] threads){
		long startTime = System.currentTimeMillis();
		int j = 1; // this is just to inner loop running until thread terminated
		for(int k=0; k<threads.length;k++){
			for(int i=0; i<j;i++){
				if(threads[k].getState() == Thread.State.TERMINATED){
					break; // breaks from inner loop when thread terminated
				}
				try {
					j++;
					threads[k].sleep(1000); // this is to verify thread state every second (sleep)
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		long endTime = System.currentTimeMillis();
		// calculating the time
		long totalTimeInSeconds = (endTime - startTime)/1000;
		long seconds = 0;
		if(totalTimeInSeconds >= 3600){
			long minutes = totalTimeInSeconds/SECONDS_PER_MINUTE;
			seconds = totalTimeInSeconds - minutes * SECONDS_PER_MINUTE;
			long hours = minutes / MINUTES_PER_HOUR;
			minutes -= hours* MINUTES_PER_HOUR;
			if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
				System.out.println(TransactionRunner.totalRecordsCount + " records processed in " +hours+" hrs, " +minutes+" mins, "+seconds+" secs @ "+TransactionRunner.totalRecordsCount/totalTimeInSeconds+ " TPS");
			}else{
				System.out.println(TransactionRunner.totalRecordsCount + " records processed in " +hours+" hrs, " +minutes+" mins, "+seconds+" secs @ "+TransactionRunner.totalRecordsCount/totalTimeInSeconds+ " TPS");
			}
		}else if(totalTimeInSeconds > 60){
			long minutes = totalTimeInSeconds/SECONDS_PER_MINUTE;
			seconds = totalTimeInSeconds - minutes * SECONDS_PER_MINUTE;
			if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
				System.out.println(TransactionRunner.totalRecordsCount + " records processed in " +minutes+" mins, "+seconds+" secs @ "+TransactionRunner.totalRecordsCount/totalTimeInSeconds+ " TPS");
			}else{
				System.out.println(TransactionRunner.totalRecordsCount + " records processed in " +minutes+" mins, "+seconds+" secs @ "+TransactionRunner.totalRecordsCount/totalTimeInSeconds+ " TPS");
			}
		}else{
			if(MongoPgConstants.DATABASE.equalsIgnoreCase(MongoPgConstants.MONGO_DB)){
				System.out.println(TransactionRunner.totalRecordsCount + " records processed in " +(endTime - startTime)/1000 + "secs @ "+TransactionRunner.totalRecordsCount/totalTimeInSeconds+ " TPS");
			}else{
				System.out.println(TransactionRunner.totalRecordsCount + " records processed in " +(endTime - startTime)/1000 + "secs @ "+TransactionRunner.totalRecordsCount/totalTimeInSeconds+ " TPS");
			}
		}
		System.exit(0);
	}
}
