package anonymousdemo;

import java.util.*;

public class AnonymousClassDemo {
	
	private String helloString = "V %s %s!\n";
	
	void hello(final String what) {
		final String mean = "means";
		
		//Anonymous Class
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.printf(helloString, mean, what);
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(timerTask, 1000, 1000);
	}

	public static void main(String[] args) {
		new AnonymousClassDemo().hello("Vendetta");
	}
}
