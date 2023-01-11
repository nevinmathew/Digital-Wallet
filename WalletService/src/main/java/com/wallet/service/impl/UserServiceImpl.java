package com.wallet.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.exception.exceptions.UserAlreadyExistsException;
import com.wallet.exception.exceptions.UserNotFoundException;
import com.wallet.model.User;
import com.wallet.repository.UserRepository;
import com.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String createUser(User user) throws Exception {
		try {

			if (userRepository.existsByAccountNumber(user.getAccountNumber())) {
				throw new UserAlreadyExistsException("This account number already exists");
			}
			user.setLastTransaction(LocalDateTime.now().toString());
			userRepository.save(user);
			return "User created";
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String updateUser(User user) throws Exception {
		try {
			var user1 = userRepository.findById(user.getId());

			if (user1.isEmpty())
				throw new UserNotFoundException("This user does not exist.");
			user.setLastTransaction(LocalDateTime.now().toString());
			userRepository.save(user);
			return "User updated";
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String partialUpdateUser(User user) throws Exception {
		try {
			User u1 = User.getInstance();
			var user1 = userRepository.findById(user.getId());

			if (user1.isEmpty())
				throw new UserNotFoundException("This user does not exist.");

			u1 = user1.get();

			if (user.getAccountNumber() != null)
				u1.setAccountNumber(user.getAccountNumber());

			if (user.getAddress() != null)
				u1.setAccountNumber(user.getAddress());

			if (user.getBalance() != null)
				u1.setBalance(user.getBalance());

			if (user.getBranch() != null)
				u1.setBranch(user.getBranch());

			if (Integer.valueOf(user.getCibilScore()) != null)
				u1.setCibilScore(user.getCibilScore());

			if (user.getContactNumber() != null)
				u1.setContactNumber(user.getContactNumber());

			user.setLastTransaction(LocalDateTime.now().toString());

			userRepository.save(user);
			return "User updated";
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public User getUser(int id) throws Exception {
		try {
			var user1 = userRepository.findById(id);
			if (user1.isEmpty())
				throw new UserNotFoundException("This user does not exist.");
			return user1.get();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		try {
			List<User> users = userRepository.findAll();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String deleteUser(int id) throws Exception {
		try {
			var userExists = userRepository.existsById(id);
			
			if (!userExists)
				throw new UserNotFoundException("This user does not exist.");
			userRepository.deleteById(id);
			
			return "User deleted";
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

}
