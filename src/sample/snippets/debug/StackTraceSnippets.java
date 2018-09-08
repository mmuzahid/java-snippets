package sample.snippets.debug;

public class StackTraceSnippets {

	public static void main(String args[]) {
		getStackTracePrintExmaple();
	}

	private static void getStackTracePrintExmaple() {
		sayHelloWithManualStackTrace();
		sayHelloWithSystemStackTrace();
	}
	
	
	public static void sayHelloWithManualStackTrace() {
		System.out.println(getHelloMessage());
		printStackTraceManually();
	}

	public static void sayHelloWithSystemStackTrace() {
		System.out.println(getHelloMessage());
		getSystemStackTrace();
	}
	
	@SuppressWarnings("static-access")
	private static void getSystemStackTrace() {
		Thread.currentThread().dumpStack();
	}

	public static String getHelloMessage() {
		return "Hello World by " + getDefaultUser();
	}
	
	public static String getDefaultUser() {
		return "Test User";
	}

	public static void printStackTraceManually() {
		// manually print stacktrace
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		for (StackTraceElement trace : traces) {
			System.out.println("File:" + trace.getFileName() 
				+ " | Class:" + trace.getClassName() 
				+ " | Method:" + trace.getMethodName()
				+ " | Line:" + trace.getLineNumber());
		}
	}

	
	
}
