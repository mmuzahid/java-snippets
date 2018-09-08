package sample.snippets.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionSnippets {

	public static void main(String[] args) {
		ReflectionTestBean bean = createInstanceExample("sample.snippets.reflection.ReflectionTestBean");
		methodInvokeExample(bean);
	}

	private static ReflectionTestBean createInstanceExample(String className) {
		try {
			Class clazz = Class.forName(className);
			ReflectionTestBean obj = (ReflectionTestBean) clazz.newInstance();
			System.out.println("Object Created: " + obj);
			return obj;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void methodInvokeExample(ReflectionTestBean bean) {
		try {
			Method method = bean.getClass().getMethod("executePublicMethod", null);
			method.invoke(bean, null);
		} catch (NoSuchMethodException e) {
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
