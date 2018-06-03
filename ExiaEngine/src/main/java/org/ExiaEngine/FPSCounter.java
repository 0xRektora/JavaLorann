package org.ExiaEngine;

class FPSCounter extends Thread {
	private long lastTime;
	private double fps; 

	/**
	 * The run of the thread.
	 */
	public void run() {
		while (true) { //TODO : add a condition for an finishable thread
			lastTime = System.nanoTime();
			try {
				Thread.sleep(1000); // longer than one frame
			} catch (InterruptedException e) {

			}
			fps = 1000000000.0 / (System.nanoTime() - lastTime); // one second(nano) divided by amount of time it takes
																	// for one frame to finish
			lastTime = System.nanoTime();
			//System.out.println(this.getFps());
		}
	}

	/**
	 *  Return the fps.
	 * @return double
	 * 		A rounded value of the fps with 2 numbers after the point.
	 */
	public double fps() {
		return Math.floor(fps * 100) / 100;
	}
	/**
	 * Getter for the fps
	 * @return String
	 * 		return a formated output of the fps ratio per %.
	 */
	public String getFps() {
		return ("FPS : " + this.fps() * 100 + "%");
	}
}