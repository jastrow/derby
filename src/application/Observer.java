package application;

import java.util.ArrayList;
import java.util.List;

public class Observer {

	public static Observer instance;
	
	public List<Subscriber> subscriber = new ArrayList<Subscriber>(); 
	
	public static Observer getInstance() {
		if(instance == null) {
			instance = new Observer();
		}
		
		return instance;
	}
	
	
	public void trigger(String trigger, Object data) {
		for(Subscriber subscriber: this.subscriber) {
			if(subscriber.getKeyword() == trigger) {
				System.out.println( subscriber.listenTo(trigger, data) );
			}
		}
	}
	
	public void addSubscriber(Object obj, String txt) {
		this.subscriber.add( new Subscriber(obj, txt) );
	}
	
	
}