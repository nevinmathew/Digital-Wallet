package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.service.WalletService;

@RestController
@RequestMapping(path = "/api/v1")
public class WalletController {

	@Autowired
	private WalletService walletService;

	// 1. Users are able to see the current balance.
	@GetMapping(path = "/balance/{id}")
	public ResponseEntity<?> getBalance(@PathVariable("id") int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(walletService.getBalance(userId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

//	2.	Users are able to add money. 
	@PostMapping(path = "/deposit/{accNo}")
	public ResponseEntity<?> depositAmount(@PathVariable("accNo") String accountNumber, @RequestParam double amount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(walletService.depositAmount(accountNumber, amount));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

//	3.	Users are able to withdraw money. 
	@PostMapping(path = "/withdraw/{accNo}")
	public ResponseEntity<?> withdrawAmount(@PathVariable("accNo") String accountNumber, double amount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(walletService.withdrawAmount(accountNumber, amount));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

//	Optional Functionality: Users can see the history of transactions.
	@GetMapping(path = "/transactions/{id}")
	public ResponseEntity<?> transactionsHistory(@PathVariable("id") int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(walletService.transactionsHistory(userId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

}
