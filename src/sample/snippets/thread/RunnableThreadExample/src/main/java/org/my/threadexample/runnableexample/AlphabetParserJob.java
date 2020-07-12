package org.my.threadexample.runnableexample;

public class AlphabetParserJob implements Runnable {

	private String textData;
	private int offset = 0;
	private final int windowSize = 10;
	private boolean completed = false;

	private StringBuilder parsedAlphabets = new StringBuilder();

	public AlphabetParserJob(String text) {
		this.textData = text;
	}

	public void run() {
		while (!completed) {
			int end = offset + windowSize;
			if (end > textData.length()) {
				end = textData.length();
			}
			String textCharacters = getTextData().substring(offset, end);
			System.out.println("AlphabetParserJob Window Processing: " + textCharacters);

			for (char c : textCharacters.toCharArray()) {
				if (Character.isAlphabetic(c)) {
					parsedAlphabets.append(c);
				}
			}

			offset += windowSize;
			if (offset > textData.length()) {
				completed = true;
				break;
			}
		}
		
		System.out.println("Parsed Alphabets: " + parsedAlphabets.toString());
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
