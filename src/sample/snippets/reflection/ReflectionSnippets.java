package sample.snippets.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionSnippets {

	public static void main(String[] args) {
		methodInvokeExample();
	}

	private static void methodInvokeExample() {
		ReflectionTestBean bean = new ReflectionTestBean();

		Method m;
		try {
			m = bean.getClass().getMethod("publicMethod", null);
			m.invoke(bean, null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}

class ReflectionTestBean {
	private int privateInt;
	public String publicString;

	ReflectionTestBean() {

	}

	ReflectionTestBean(int privateInt, String publicString) {
		this.privateInt = privateInt;
		this.publicString = publicString;
	}

	private void privateMethod() {
		System.out.println("privateMethod invoked!");
	}

	public void publicMethod() {
		System.out.println("publicMethod invoked!");
	}
}
