package com.esl.iso;

public class AlphaNumericSpecialFieldValidator implements IFieldValidator {

	public String getDescription() {
		return "ans";
	}

	public boolean isValid(String value) {
		byte[] valueBytes = value.getBytes();
		for (byte b : valueBytes)
			if (b < 32)
				return false;
		return true;
	}

}
