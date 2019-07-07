package sample.snippets.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
Run with following JVM option:
java -Xmx4M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:/java_heapdump.hprof HeapDumpExampleOnOOM	

*/
public class HeapDumpExampleOnOOM {

	public static void main(String[] args) {
		System.out.println("Execution start");
		//createStackoverflowError();
		createOOMException();
		System.out.println("Execution end");		
	}

	
	public static void createOOMException() {
		List<String> list = new ArrayList<String>();
		int i = 0;
		Random rand = new Random();
		try {
			while(++i > 0) {
				list.add(new String("S-" + rand.nextInt()));
			}	
		} catch (OutOfMemoryError e) {
			System.out.println(new String("OutOfMemoryError: " + " i : " + i));
		}		
	}


	public static void createStackoverflowError() {
		createStackoverflowError();
	}
}
