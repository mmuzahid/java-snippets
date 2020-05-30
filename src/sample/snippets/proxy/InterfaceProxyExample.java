package practice.java.snippets;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxyExample {

	public static void main(String[] args) {
		// original / target object
		RestService service = new RestService();
		// proxy will call invoke method of this handler
		ServiceInvocationHandler serviceHandler = new ServiceInvocationHandler(service);
		// Create proxy object from the interface and handler
		ServiceProxyInterface proxy = (ServiceProxyInterface) Proxy.newProxyInstance(
				ServiceProxyInterface.class.getClassLoader(), new Class[] { ServiceProxyInterface.class },
				serviceHandler);
		// method() of proxy object will hit target method() via handler invoke() method
		proxy.get("hello");
		proxy.post("welcome");
	}

}

interface ServiceProxyInterface {
	String get(String s);

	String post(String s);
}

class RestService implements ServiceProxyInterface {
	public String get(String s) {
		System.out.println("executing get method... param:" + s);
		return "GET: " + s;
	}

	public String post(String s) {
		System.out.println("executing post method... param:" + s);
		return "POST: " + s;
	}
}

class ServiceInvocationHandler implements InvocationHandler {
	private final Object target;

	public ServiceInvocationHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("LOG: START");
		System.out.println("LOG: method: " + method);
		System.out.println("LOG: args: " + Arrays.toString(args));
		System.out.println("LOG: target: " + target);
		Object returnValue = method.invoke(target, args);
		System.out.println("LOG: returnValue: " + returnValue);
		System.out.println("LOG: END");
		return returnValue;
	}
}