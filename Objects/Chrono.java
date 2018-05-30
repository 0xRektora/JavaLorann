public class Chrono implements Runnable {

	private final int pause = 3;
	Monster monster;
	
	public Chrono(Monster monster) {
		this.monster = monster;
	}
	
	@Override	
	public void run() {			
		while(true) {
			
			System.out.println("oyu");
			this.monster.setX(monster.getX()+10);
			
			try {
				
			Thread.sleep(30);
			}
			catch(Exception e) {
			}
				
	
		
			}
	}

}
