package org.cephas.acdia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class PasswordResetLinkException extends RuntimeException {

	private String user;
	private String message;

	public PasswordResetLinkException(String user, String message) {
		super(String.format("Failed to send password reset link to User[%d] : '%s'", user, message));
		this.user = user;
		this.message = message;
	}
}