package MotionElement;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Animation extends TimerTask{
	private List<String> assets;
	 
	Timer timer;
	
	public Animation() {
		this.timer = new Timer();
		this.timer.schedule(this, 300);
	}
	
	public void animate() {
		
	}


	@Override
	public void run() {
		for(String i : assets) {
			
		}
		
	}

}
