package com.wallet.exception.exceptions;


public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2320093702365937461L;

	public UserNotFoundException() {}

	public UserNotFoundException(String message) {
		super(message);
	}

}
