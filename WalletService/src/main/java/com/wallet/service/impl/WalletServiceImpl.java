package com.wallet.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.model.TransactionsLog;
import com.wallet.model.User;
import com.wallet.model.enumeration.TransactionType;
import com.wallet.repository.TransactionsLogRepository;
import com.wallet.repository.UserRepository;
import com.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TransactionsLogRepository transactionsRepo;

	@Override
	public String getBalance(int userId) {
//		(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();   userId or accountNumber is found using spring security
		
		Optional<User> user = userRepo.findById(userId);
		
		return user.isPresent() ? String.valueOf(user.get().getBalance()) :  "no such user";
			
	}

	@Override
	public String depositAmount(String accountNumber, double amount) {

		User user = userRepo.findByAccountNumber(accountNumber);

		user.setBalance(user.getBalance().add(BigDecimal.valueOf(amount)));
		userRepo.save(user);

		TransactionsLog transactions = new TransactionsLog();
		transactions.setAmount(user.getBalance().add(BigDecimal.valueOf(amount)));
		transactions.setTransactionType(TransactionType.deposit.toString());
		transactions.setTransactionAt(LocalDateTime.now().toString());
		transactions.setAccountNumber(accountNumber);
		transactions.setUserId(user.getId());
		transactionsRepo.save(transactions);

		return "Amount deposited";
	}

	@Override
	public String withdrawAmount(String accountNumber, double amount) {

		User user = userRepo.findByAccountNumber(accountNumber);

		if (user.getBalance().subtract(BigDecimal.valueOf(amount)).compareTo(BigDecimal.valueOf(500)) >= 0)	//minimum balance: 500
			user.setBalance(user.getBalance().subtract(BigDecimal.valueOf(amount)));
		userRepo.save(user);

		TransactionsLog transactions = new TransactionsLog();
		transactions.setAmount(user.getBalance().add(BigDecimal.valueOf(amount)));
		transactions.setTransactionType(TransactionType.withdrawal.toString());
		transactions.setTransactionAt(LocalDateTime.now().toString());
		transactions.setAccountNumber(accountNumber);
		transactions.setUserId(user.getId());
		transactionsRepo.save(transactions);

		return "Amount withdrawn";
	}

	@Override
	public List<TransactionsLog> transactionsHistory(int userId) {
		return transactionsRepo.findByUserId(userId);
	}

}
