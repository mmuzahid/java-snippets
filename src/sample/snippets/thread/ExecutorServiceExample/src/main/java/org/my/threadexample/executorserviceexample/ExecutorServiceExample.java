package org.my.threadexample.executorserviceexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

	public static final int POOL_SIZE = 10; 
	
	public static void main(String[] args) {
		String text = "7Afjf3494n,mvdnodsng;oagnd;fgg3409j0X";
		
		AlphabetParserJob alphabetParserJob = new AlphabetParserJob(text);
		DigitParserJob digitParserJob = new DigitParserJob(text);

		ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

		executorService.execute(alphabetParserJob);
		executorService.execute(digitParserJob);

	}

}
