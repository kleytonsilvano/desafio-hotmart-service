package com.hotmart.models.enums;

public enum OrderByEnum {

	NAME,
	SCORE,
	CATEGORY;
	
	public Boolean isName() {
		return this.equals(NAME);
	}

	public Boolean isScore() {
		return this.equals(SCORE);
	}
	
	public Boolean isCategory() {
		return this.equals(CATEGORY);
	}
}
