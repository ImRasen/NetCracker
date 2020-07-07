package nesteddemo_1;

import java.util.*;

public class StaticNestedDemo {
	
	private String helloString = "Hello, World!";
	
	//Static Nested Class 
	static class HelloTimerTask extends TimerTask {
		
		private StaticNestedDemo outer;
		
		public HelloTimerTask(StaticNestedDemo outer) {
			this.outer = outer;
		}
		
		@Override
		public void run() {
			System.out.println(outer.helloString);
		}
	}

	void helloWorld() {
		TimerTask timerTask = new StaticNestedDemo.HelloTimerTask(this);
		Timer timer = new Timer();
		timer.schedule(timerTask, 1000, 1000);
	}
	
	public static void main(String[] args) {
		new StaticNestedDemo().helloWorld();
	}
}
