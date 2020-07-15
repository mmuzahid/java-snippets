package org.my.threadoperationsexample.job;

import org.my.threadoperationsexample.lock.SharedLocks;

public class RestApiDemoJob implements Runnable {

	public RestApiDemoJob() {
	}

	public void run() {
		synchronized (SharedLocks.RestApiLock) {
			System.out.println("RestApiDemoJob: Holding SharedLocks.RestApiLock...");

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			System.out.println("RestApiDemoJob: Waiting for SharedLocks.DbLock...");

			synchronized (SharedLocks.DbLock) {
				System.out.println("RestApiDemoJob: Holding SharedLocks.DbLock & SharedLocks.RestApiLock...");
			}
		}
		
		
		System.out.println("RestApiDemoJob: excution done...");
		
	}

}
