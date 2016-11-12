package application;

import java.lang.reflect.InvocationTargetException;

public class Subscriber {
	
	public String keyword;
	public Object listener;
	
	public Subscriber(Object subscriber, String keyword) {
		this.keyword = keyword;
		this.listener = subscriber;
	}
	public Subscriber(String keyword, Object subscriber) {
		this.keyword = keyword;
		this.listener = subscriber;
	}
	
	public String listenTo(String keyword, Object daten) {
		java.lang.reflect.Method method = null;
        Class[] cArg = new Class[1];
        cArg[0] = Object.class;
		try {
			method = this.listener.getClass().getMethod("calling", cArg);
		} catch (SecurityException e) {  System.out.println("B1"); }
		  catch (NoSuchMethodException e) {  System.out.println("B2"); }
		
		try {
			method.invoke(this.listener, daten);
		} catch (IllegalArgumentException e) { }
		  catch (IllegalAccessException e) { }
		  catch (InvocationTargetException e) { }
		
		return "wurde ausgeführt";
	}
	
	public String getKeyword() {
		return keyword;
	}
}
