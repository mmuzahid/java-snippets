package sample.snippets.debug;

public class StackTraceSnippets {

	public static void main(String args[]) {
		getStackTracePrintExmaple();
	}

	private static void getStackTracePrintExmaple() {
		HelloObject helloObject = new HelloObject();
		try {
			helloObject.sayHelloWithPrintTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class HelloObject {
	public void sayHelloWithPrintTrace() throws Exception {
		System.out.println(getHelloMessage());
		printStackTraceManually();
		getSystemStackTrace();
		throw new Exception();
	}

	@SuppressWarnings("static-access")
	private void getSystemStackTrace() {
		Thread.currentThread().dumpStack();		
	}

	public String getHelloMessage() {
		return "Hello World by " + getDefaultUser();
	}
	
	public String getDefaultUser() {
		return "Test User";
	}

	public void printStackTraceManually() {
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
