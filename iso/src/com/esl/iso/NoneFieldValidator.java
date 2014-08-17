package com.esl.iso;

public class NoneFieldValidator implements IFieldValidator {

	public String getDescription() {
		return "none";
	}

	public boolean isValid(String value) {
		return true;
	}

}
