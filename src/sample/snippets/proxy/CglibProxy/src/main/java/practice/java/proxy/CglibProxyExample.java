package practice.java.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class CglibProxyExample {

	public static void main(String[] args) {
		System.out.println("CglibProxyExample");
		
		MyService service = new MyService();
		MyCglibInvocationHandler serviceHandler = new MyCglibInvocationHandler(service);
		MyService serviceProxy = (MyService) Enhancer.create(MyService.class, serviceHandler);
		
		System.out.println("service == serviceProxy : " + (service == serviceProxy));
		System.out.println("service class name : " + service.getClass().getCanonicalName());
		System.out.println("serviceProxy class name : " + serviceProxy.getClass().getCanonicalName());
		
		serviceProxy.get("get data");
		serviceProxy.post("post data");
	}

}

class MyService {
	public String get(String s) {
		System.out.println("executing get method... param:" + s);
		return "GET: " + s;
	}

	public String post(String s) {
		System.out.println("executing post method... param:" + s);
		return "POST: " + s;
	}

}

class MyCglibInvocationHandler implements InvocationHandler {
	private final Object target;

	public MyCglibInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
