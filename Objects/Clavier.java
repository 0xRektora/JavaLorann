import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Clavier implements KeyListener {


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Fenetre.pan.joueur.setX(Fenetre.pan.joueur.getX()+25);
			Fenetre.pan.joueur.setDirection(Direction.RIGHT);
			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_r.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Fenetre.pan.joueur.setX(Fenetre.pan.joueur.getX()-25);
			Fenetre.pan.joueur.setDirection(Direction.LEFT);

			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_l.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			Fenetre.pan.joueur.setY(Fenetre.pan.joueur.getY()-25);
			Fenetre.pan.joueur.setDirection(Direction.UP);

			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_u.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Fenetre.pan.joueur.setY(Fenetre.pan.joueur.getY()+25);
			Fenetre.pan.joueur.setDirection(Direction.DOWN);

			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_b.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP && e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Fenetre.pan.joueur.setY(Fenetre.pan.joueur.getY()-25);
			Fenetre.pan.joueur.setX(Fenetre.pan.joueur.getX()+25);
			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_ur.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP && e.getKeyCode() == KeyEvent.VK_LEFT) {
			Fenetre.pan.joueur.setY(Fenetre.pan.joueur.getY()-25);
			Fenetre.pan.joueur.setX(Fenetre.pan.joueur.getX()-25);
			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_ul.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getKeyCode() == KeyEvent.VK_LEFT) {
			Fenetre.pan.joueur.setY(Fenetre.pan.joueur.getY()-25);
			Fenetre.pan.joueur.setX(Fenetre.pan.joueur.getX()-25);

			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_bl.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Fenetre.pan.joueur.setY(Fenetre.pan.joueur.getY()+25);
			Fenetre.pan.joueur.setX(Fenetre.pan.joueur.getX()+25);
			try {
				Fenetre.pan.setIconeJoueur("lizimaj/lorann_br.png");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
