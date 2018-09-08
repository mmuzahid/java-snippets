package sample.snippets.reflection;

class ReflectionTestBean {
	private int privateInt;
	public String publicString;

	ReflectionTestBean() {

	}

	ReflectionTestBean(int privateInt, String publicString) {
		this.privateInt = privateInt;
		this.publicString = publicString;
	}

	private void executePrivateMethod() {
		System.out.println("PrivateMethod invoked!");
	}

	public void executePublicMethod() {
		System.out.println("PublicMethod invoked!");
	}
}