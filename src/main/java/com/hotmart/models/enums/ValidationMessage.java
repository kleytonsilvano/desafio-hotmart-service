package com.hotmart.models.enums;

public enum ValidationMessage {
	
	SALESMAN_NOT_FOUND("Salesman not found!"),
	PRODUCT_NOT_FOUND("Product not found!");
	
	private String message;
	
	private ValidationMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
