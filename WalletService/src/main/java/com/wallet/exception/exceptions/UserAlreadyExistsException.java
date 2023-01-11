package com.wallet.exception.exceptions;


public class UserAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2320093702365937461L;

	public UserAlreadyExistsException() {}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
