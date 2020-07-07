package localdemo;

import java.util.*;

public class LocalClassDemo {
	
	private String helloString = "Hello, %s,%s!\n";
	
	void hello(final String who) {
		final String mr = "Mr. " + new String("Im");
		
		//Local Class (inside of method
		class HelloTimerTask extends TimerTask {
			
			@Override
			public void run() {
				System.out.printf(helloString, mr, who);
			}
		}
		
		TimerTask timerTask = new HelloTimerTask();
		Timer timer = new Timer();
		timer.schedule(timerTask, 1000, 1000);
	}
	
	public static void main(String[] args) {
		new LocalClassDemo().hello("Big World");
	}

}
