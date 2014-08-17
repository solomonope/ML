package com.esl.iso;

public class Validators {
	public static boolean isHex(String value) {
		HexFieldValidator validator = new HexFieldValidator();
		return validator.isValid(value);
	}

	public static boolean isNumeric(String value) {
		NumericFieldValidator validator = new NumericFieldValidator();
		return validator.isValid(value);
	}
}
