package org.my.threadoperationsexample.example;

import org.my.threadoperationsexample.job.DbUpdateDemoJob;
import org.my.threadoperationsexample.job.RestApiDemoJob;

public class DeadLockExample {
	public static void showDeadLockExample() {
		RestApiDemoJob restApiDemoJob = new RestApiDemoJob();
		DbUpdateDemoJob dbUpdateDemoJob = new DbUpdateDemoJob();
		new Thread(restApiDemoJob).start();
		new Thread(dbUpdateDemoJob).start();
	}
}
