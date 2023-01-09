package com.wallet.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wallet.model.TransactionsLog;
import com.wallet.model.User;
import com.wallet.model.enumeration.TransactionType;
import com.wallet.repository.TransactionsLogRepository;
import com.wallet.repository.UserRepository;

@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
//@DataJpaTest
class WalletServiceImplTest {

	@InjectMocks
	WalletServiceImpl walletService = new WalletServiceImpl();
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private TransactionsLogRepository transactionsRepo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getBalanceTest(){
		
		User user = User.getInstance();

		user.setId(1); 
		user.setAccountNumber("10001"); 
		user.setAddress("porur");
		user.setBalance(BigDecimal.valueOf(800.00));
		user.setBranch("porur"); 
		user.setCibilScore(612); 
		user.setContactNumber("9988776655"); 
		user.setLastTransaction(LocalDateTime.now().toString());
		user.setName("name1"); 
		user.setTransactionMode("credit");
		
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		user = userRepo.findById(1).get();
		
		assertThat(user.getBalance()).isEqualTo(BigDecimal.valueOf(800.00));
		
		assertThat(walletService.getBalance(1)).isEqualTo(String.valueOf(800.00));
	}

	@Test
	void getBalanceTest2(){

		User user = User.getInstance();

		user.setId(2); 
		user.setAccountNumber("10002"); 
		user.setAddress("perungudi");
		user.setBalance(BigDecimal.valueOf(1500.00));
		user.setBranch("porur"); 
		user.setCibilScore(619); 
		user.setContactNumber("9988776607"); 
		user.setLastTransaction(LocalDateTime.now().toString());
		user.setName("name2"); 
		user.setTransactionMode("debit");
		
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		User u2 = userRepo.findById(2).get();
		
		assertThat(u2.getBalance()).isEqualTo(BigDecimal.valueOf(1500.00));
		
		assertThat(walletService.getBalance(2)).isEqualTo(String.valueOf(1500.00));

	}
	
	
	@Test
	void depositAmountTest() {
		
		User user = User.getInstance();

		user.setId(1); 
		user.setAccountNumber("10001"); 
		user.setAddress("porur");
		user.setBalance(BigDecimal.valueOf(800.00));
		user.setBranch("porur"); 
		user.setCibilScore(612); 
		user.setContactNumber("9988776655"); 
		user.setLastTransaction(LocalDateTime.now().toString());
		user.setName("name1"); 
		user.setTransactionMode("credit");
		
		when(userRepo.findByAccountNumber(anyString())).thenReturn(user);
		user = userRepo.findByAccountNumber("10001");
		userRepo.save(user);
		verify(userRepo, times(1)).save(user);
		
		TransactionsLog transactions = new TransactionsLog();
		when(transactionsRepo.findByAccountNumber(anyString())).thenReturn(transactions);
		transactions = transactionsRepo.findByAccountNumber("10001");
		transactions.setAmount(user.getBalance().add(BigDecimal.valueOf(100)));
		transactions.setTransactionType(TransactionType.deposit.toString());
		transactions.setTransactionAt(LocalDateTime.now().toString());
		transactionsRepo.save(transactions);
		verify(transactionsRepo, times(1)).save(transactions);
		
		assertThat(walletService.depositAmount("10001",100)).isEqualTo("Amount deposited");
	}
	
	@Test
	void depositAmountTest2() {

		User user = User.getInstance();

		user.setId(2); 
		user.setAccountNumber("10002"); 
		user.setAddress("perungudi");
		user.setBalance(BigDecimal.valueOf(1500.00));
		user.setBranch("porur"); 
		user.setCibilScore(619); 
		user.setContactNumber("9988776607"); 
		user.setLastTransaction(LocalDateTime.now().toString());
		user.setName("name2"); 
		user.setTransactionMode("debit");
		
		when(userRepo.findByAccountNumber(anyString())).thenReturn(user);
		user = userRepo.findByAccountNumber("10002");
		userRepo.save(user);
		verify(userRepo, times(1)).save(user);
		
		TransactionsLog transactions = new TransactionsLog();
		when(transactionsRepo.findByAccountNumber(anyString())).thenReturn(transactions);
		transactions = transactionsRepo.findByAccountNumber("10002");
		transactions.setAmount(user.getBalance().add(BigDecimal.valueOf(100)));
		transactions.setTransactionType(TransactionType.deposit.toString());
		transactions.setTransactionAt(LocalDateTime.now().toString());
		transactionsRepo.save(transactions);
		verify(transactionsRepo, times(1)).save(transactions);
		
		assertThat(walletService.depositAmount("10002",100)).isEqualTo("Amount deposited");
	}
	
	
	@Test
	void withdrawAmountTest() {
		User user = User.getInstance();

		user.setId(1); 
		user.setAccountNumber("10001"); 
		user.setAddress("porur");
		user.setBalance(BigDecimal.valueOf(800.00));
		user.setBranch("porur"); 
		user.setCibilScore(612); 
		user.setContactNumber("9988776655"); 
		user.setLastTransaction(LocalDateTime.now().toString());
		user.setName("name1"); 
		user.setTransactionMode("credit");
		
		when(userRepo.findByAccountNumber(anyString())).thenReturn(user);
		user = userRepo.findByAccountNumber("10001");
		userRepo.save(user);
		verify(userRepo, times(1)).save(user);
		
		TransactionsLog transactions = new TransactionsLog();
		when(transactionsRepo.findByAccountNumber(anyString())).thenReturn(transactions);
		transactions = transactionsRepo.findByAccountNumber("10001");
		transactions.setAmount(user.getBalance().subtract(BigDecimal.valueOf(100)));
		transactions.setTransactionType(TransactionType.withdrawal.toString());
		transactions.setTransactionAt(LocalDateTime.now().toString());
		transactionsRepo.save(transactions);
		verify(transactionsRepo, times(1)).save(transactions);
		
		assertThat(walletService.withdrawAmount("10001",100)).isEqualTo("Amount withdrawn");
	}
	
	@Test
	void withdrawAmountTest2() {
		User user = User.getInstance();

		user.setId(2); 
		user.setAccountNumber("10002"); 
		user.setAddress("perungudi");
		user.setBalance(BigDecimal.valueOf(1500.00));
		user.setBranch("porur"); 
		user.setCibilScore(619); 
		user.setContactNumber("9988776607"); 
		user.setLastTransaction(LocalDateTime.now().toString());
		user.setName("name2"); 
		user.setTransactionMode("debit");
		
		when(userRepo.findByAccountNumber(anyString())).thenReturn(user);
		user = userRepo.findByAccountNumber("10002");
		userRepo.save(user);
		verify(userRepo, times(1)).save(user);
		
		TransactionsLog transactions = new TransactionsLog();
		when(transactionsRepo.findByAccountNumber(anyString())).thenReturn(transactions);
		transactions = transactionsRepo.findByAccountNumber("10002");
		transactions.setAmount(user.getBalance().subtract(BigDecimal.valueOf(100)));
		transactions.setTransactionType(TransactionType.withdrawal.toString());
		transactions.setTransactionAt(LocalDateTime.now().toString());
		transactionsRepo.save(transactions);
		verify(transactionsRepo, times(1)).save(transactions);
		
		assertThat(walletService.withdrawAmount("10002",100)).isEqualTo("Amount withdrawn");
	}
}
