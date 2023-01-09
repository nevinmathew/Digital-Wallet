package com.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.model.TransactionsLog;

@Repository
public interface TransactionsLogRepository extends JpaRepository<TransactionsLog, Integer> {

	List<TransactionsLog> findByUserId(int userId);


	TransactionsLog findByAccountNumber(String accountNumber);

}
