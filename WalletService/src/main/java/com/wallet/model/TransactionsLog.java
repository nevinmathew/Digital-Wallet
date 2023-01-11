package com.wallet.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions_log")
public class TransactionsLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "transaction_type")
	private String transactionType; // (enum)

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "transaction_at")
	private String transactionAt;

//	private TransactionsLog() {}
//	
//	//BillPugh Singleton implementation
//	private static class SingletonHelper {
//		private static final TransactionsLog INSTANCE = new TransactionsLog();
//	}
//	
//	public static TransactionsLog getInstance() {
//		return SingletonHelper.INSTANCE;
//	}

	public TransactionsLog() {
		super();
	}

	public TransactionsLog(int id, int userId, String accountNumber, String transactionType, BigDecimal amount,
			String transactionAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionAt = transactionAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransactionAt() {
		return transactionAt;
	}

	public void setTransactionAt(String transactionAt) {
		this.transactionAt = transactionAt;
	}

	@Override
	public String toString() {
		return "TransactionsLog [id=" + id + ", userId=" + userId + ", accountNumber=" + accountNumber
				+ ", transactionType=" + transactionType + ", amount=" + amount + ", transactionAt=" + transactionAt
				+ "]";
	}

}
