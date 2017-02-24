package com.capitalone.mongopgres.mongo.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"amount","transactionType","transactionTime"})
public class Transactions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("amount")
	private Integer amount;
	
	@JsonProperty("transactionType")
	private String transactionType;
	
	@JsonProperty("transactionTime")
	private String transactionTime;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		return "Transactions [amount=" + amount + ", transactionType="
				+ transactionType + ", transactionTime=" + transactionTime
				+ "]";
	}
	
}
