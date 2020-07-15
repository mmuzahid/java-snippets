package org.my.threadoperationsexample.job;

import org.my.threadoperationsexample.lock.SharedLocks;

public class DbUpdateDemoJob implements Runnable {

	public DbUpdateDemoJob() {
	}

	public void run() {

		synchronized (SharedLocks.DbLock) {
			System.out.println("DbUpdateDemoJob: Holding SharedLocks.DbLock...");

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			System.out.println("DbUpdateDemoJob: Waiting for SharedLocks.RestApiLock...");

			synchronized (SharedLocks.RestApiLock) {
				System.out.println("DbUpdateDemoJob: Holding SharedLocks.DbLock & SharedLocks.RestApiLock...");
			}
		}
		
		System.out.println("DbUpdateDemoJob: excution done...");

	}

}
