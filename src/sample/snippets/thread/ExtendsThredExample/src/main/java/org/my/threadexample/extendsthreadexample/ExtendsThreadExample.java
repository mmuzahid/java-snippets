package org.my.threadexample.extendsthreadexample;

public class ExtendsThreadExample {

	public static void main(String[] args) {
		String text = "8Afjf3494n,mvdnodsng;oagnd;fgg3409jgZ";
		
		AlphabetParserJob alphabetParserJob = new AlphabetParserJob(text);
		DigitParserJob digitParserJob = new DigitParserJob(text);

		alphabetParserJob.start();
		digitParserJob.start();		
	}

}
