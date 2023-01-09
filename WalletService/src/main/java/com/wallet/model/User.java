package com.wallet.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Column(name = "last_transaction")
	private String lastTransaction;
	
	@Column(name = "transaction_mode")
	private String transactionMode; //credit or debit
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "cibil_score")
	private int cibilScore;
	
	private User() {}
	
	//BillPugh Singleton implementation
	private static class SingletonHelper {
		private static final User INSTANCE = new User();
	}
	
	public static User getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getLastTransaction() {
		return lastTransaction;
	}

	public void setLastTransaction(String lastTransaction) {
		this.lastTransaction = lastTransaction;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getCibilScore() {
		return cibilScore;
	}
	
	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	@Override
	public String toString() {
		return "User [id=" + id 
				+ ", name=" + name 
				+ ", accountNumber=" + accountNumber 
				+ ", branch=" + branch
				+ ", balance=" + balance 
				+ ", lastTransaction=" + lastTransaction 
				+ ", transactionMode=" + transactionMode 
				+ ", address=" + address 
				+ ", contactNumber=" + contactNumber 
				+ ", cibilScore=" + cibilScore + "]";
	}


	
}
