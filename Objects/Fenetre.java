import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame implements Runnable {

	public static Pannel pan = new Pannel();
	private final int pause = 3;
	

	
	private Thread th = new Thread() {
		@Override
		public void run() {
			
			while(true) {
				pan.repaint();
				
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {}
			
			}
		}
	};
	public Fenetre() {

		this.setTitle("Fenetre");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		pan.setBackground(Color.BLACK);
		this.setContentPane(pan);
		this.setVisible(true);
		Thread th2 = new Thread(new Monster(10, 10));
		th2.start();
		th.start();
	}


	
	
}
