package com.wallet.service;

import java.util.List;

import com.wallet.model.User;

public interface UserService {

	public String createUser(User user) throws Exception;

	public String updateUser(User user) throws Exception;

	public String partialUpdateUser(User user) throws Exception;

	public User getUser(int id) throws Exception;

	public List<User> getAllUsers() throws Exception;

	public String deleteUser(int id) throws Exception;

}
