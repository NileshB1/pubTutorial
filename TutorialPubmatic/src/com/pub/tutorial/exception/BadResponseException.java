package com.pub.tutorial.exception;

public class BadResponseException extends Exception {
	private static final long serialVersionUID = -7571406600787829787L;
	String expMeassage;

	public BadResponseException(String expMeassage) {
		super(expMeassage);
		this.expMeassage = expMeassage;
	}
}
