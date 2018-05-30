
public class Monster extends MotionElement {
	
	private boolean isAlive = true;
	
	public Monster(int x, int y) {
		
		this.x =x;
		this.y =x;
		this.isAlive = true;
		
	}
	
		@Override	
		public void run() {			
			while(true) {
				try {
					
				Thread.sleep(300);
				}
				catch(Exception e) {
				}
					
			System.out.println("oyu");
			setX(getX()+10);
			
				}
		
		
		}
		public boolean setDead() {
			return this.isAlive = false;
		}
		
		@Override
		public int getX() {
			// TODO Auto-generated method stub
			return super.getX();
		}
	
	}
