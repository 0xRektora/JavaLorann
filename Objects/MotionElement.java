import java.awt.image.BufferedImage;

public class MotionElement implements Runnable {
	
	public BufferedImage image;
	public static int x;
	public static int y;
	
	public  int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
    

}
