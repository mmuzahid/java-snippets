package org.my.threadexample.runnableexample;

public class DigitParserJob implements Runnable {

	private String textData;
	private int offset = 0;
	private final int windowSize = 10;
	private boolean completed = false;

	private StringBuilder parsedDigits = new StringBuilder();

	public DigitParserJob(String text) {
		this.textData = text;
	}

	public void run() {
		while (!completed) {
			int end = offset + windowSize;
			if (end > textData.length()) {
				end = textData.length();
			}
			String textCharacters = getTextData().substring(offset, end);
			System.out.println("DigitParserJob Window Processing: " + textCharacters);
			
			for (char c : textCharacters.toCharArray()) {
				if (Character.isDigit(c)) {
					parsedDigits.append(c);
				}
			}

			offset += windowSize;
			if (offset > textData.length()) {
				completed = true;
				break;
			}

		}

		System.out.println("Parsed Digits: " + parsedDigits.toString());

	}

	public String getTextData() {
		return textData;
	}

	public void setTextData(String text) {
		this.textData = text;
	}

	public boolean isCompleted() {
		return completed;
	}

}
