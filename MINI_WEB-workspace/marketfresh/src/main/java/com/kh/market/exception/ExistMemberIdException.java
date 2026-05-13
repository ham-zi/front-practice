package com.kh.market.exception;

public class ExistMemberIdException extends RuntimeException {

	public ExistMemberIdException() {
	}

	public ExistMemberIdException(String message) {
		super(message);

	}

	public ExistMemberIdException(Throwable cause) {
		super(cause);

	}

	public ExistMemberIdException(String message, Throwable cause) {
		super(message, cause);

	}

	public ExistMemberIdException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
