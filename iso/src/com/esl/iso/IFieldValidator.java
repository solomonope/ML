package com.esl.iso;

public interface IFieldValidator {
	public String getDescription();

	public boolean isValid(String value);
}
