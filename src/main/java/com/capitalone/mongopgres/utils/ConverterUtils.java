package com.capitalone.mongopgres.utils;

import java.io.IOException;

import org.bson.Document;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.capitalone.mongopgres.mongo.model.Customer;
/**
 * This is a utility class to convert json object to java, and java object to json string
 * @author NFT887
 *
 */
public class ConverterUtils {
	public static  Object JsonToJavaObjectConverter(String jsonString,Object obj){
		ObjectMapper mapper = new ObjectMapper();
		try {
			obj =  mapper.readValue(jsonString, obj.getClass());
			 Customer customer = (Customer)obj;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	@SuppressWarnings("static-access")
	public static Document JavaToJsonConverter(Object object){
		ObjectMapper mapper = new ObjectMapper();
		Document document = new Document();
		try {
			String jsonString = mapper.writeValueAsString(object);
			document = document.parse(jsonString);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	public static String getAccount(int number, int threadNumber){
		String first8 = String.format("%08d", number);
		String NineAndTenDigits = String.format("%02d", threadNumber);
		String lastDigit = first8.substring(7);
		String accId = first8.concat(NineAndTenDigits).concat(lastDigit);
		return accId;
	}
}
