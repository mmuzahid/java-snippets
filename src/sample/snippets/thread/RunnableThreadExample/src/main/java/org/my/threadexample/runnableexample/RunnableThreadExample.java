package org.my.threadexample.runnableexample;

public class RunnableThreadExample {

	public static void main(String[] args) {
		String text = "9ffjf3494n,mvdnodsng;oagnd;fgg3409jgZ";
		
		AlphabetParserJob alphabetParserJob = new AlphabetParserJob(text);
		DigitParserJob digitParserJob = new DigitParserJob(text);

		Thread t1 = new Thread(alphabetParserJob);
		Thread t2 = new Thread(digitParserJob);
		
		t1.start();
		t2.start();		
	}

}
