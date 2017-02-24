package com.capitalone.mongopgres.mongo.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
/**
 * customer object for mongodb
 * @author nft887
 *
 */
@JsonPropertyOrder({"accountId","sortId","rewardsBalance","eligibilityIndicator","transactions"})
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String _id;
	
	@JsonProperty("accountId")
	private String accountId;
	
	@JsonProperty("sortId")
	private String sortId;
	
	@JsonProperty("rewardsBalance")
	private String rewardsBalance;
	
	@JsonProperty("eligibilityIndicator")
	private String eligibilityIndicator;
	
	@JsonProperty("transactions")
	private List<Transactions> transactions;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getRewardsBalance() {
		return rewardsBalance;
	}

	public void setRewardsBalance(String rewardsBalance) {
		this.rewardsBalance = rewardsBalance;
	}

	public String getEligibilityIndicator() {
		return eligibilityIndicator;
	}

	public void setEligibilityIndicator(String eligibilityIndicator) {
		this.eligibilityIndicator = eligibilityIndicator;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Customer [_id=" + _id + ", accountId=" + accountId
				+ ", sortId=" + sortId + ", rewardsBalance=" + rewardsBalance
				+ ", eligibilityIndicator=" + eligibilityIndicator
				+ ", transactions=" + transactions + "]";
	}


	
}
