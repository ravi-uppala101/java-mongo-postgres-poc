package com.capitalone.mongopgres.postgres.model;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * customer object for postgres
 * @author nft887
 *
 */
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	
	private Integer sortId;
	
	private Integer rewardsBalance;
	
	private String eligibilityIndicator;
	


	public Customer() {}



	public Integer getAccountId() {
		return accountId;
	}



	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}



	public Integer getSortId() {
		return sortId;
	}



	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}



	public Integer getRewardsBalance() {
		return rewardsBalance;
	}



	public void setRewardsBalance(Integer rewardsBalance) {
		this.rewardsBalance = rewardsBalance;
	}



	public String getEligibilityIndicator() {
		return eligibilityIndicator;
	}



	public void setEligibilityIndicator(String eligibilityIndicator) {
		this.eligibilityIndicator = eligibilityIndicator;
	}



	@Override
	public String toString() {
		return "Customer [accountId=" + accountId + ", sortId=" + sortId
				+ ", rewardsBalance=" + rewardsBalance
				+ ", eligibilityIndicator=" + eligibilityIndicator + "]";
	}

}
