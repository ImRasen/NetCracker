package innerdemo;

import java.util.*;

//Outer Class
public class InnerClassDemo {
	
	private String helloString = "Hello, inner world!";
	
	//Inner Class
	class HelloTimerTask extends TimerTask {
		
		@Override
		public void run() {
			System.out.println(helloString);
		}
	}
	
	void helloWorld() {
		TimerTask timerTask = this.new HelloTimerTask(); //"this" is optional (for external)
		Timer timer = new Timer();
		timer.schedule(timerTask, 1000, 1000);
	}
	
	public static void main(String[] args) {
		new InnerClassDemo().helloWorld();
	}
}
