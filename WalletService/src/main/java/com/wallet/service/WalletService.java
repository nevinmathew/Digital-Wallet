package com.wallet.service;

import java.util.List;

import com.wallet.model.TransactionsLog;

public interface WalletService {

	public String getBalance(int userId);

	public String depositAmount(String accountNumber, double amount);

	public String withdrawAmount(String accountNumber, double amount);

	public List<TransactionsLog> transactionsHistory(int userId);

}
