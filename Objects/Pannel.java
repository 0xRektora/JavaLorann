import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.*;
import java.lang.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pannel extends JPanel {
	

	private BufferedImage image;
	private BufferedImage image2;

	public Joueur joueur = new Joueur();
	public String IconeJoueur = "lizimaj/lorann_u.png";
	public String IconeMonstre1 = "lizimaj/monster_1.png";
	public String IconeMonstre2 = "lizimaj/monster_2.png";
	public String IconeMonstre3 = "lizimaj/monster_3.png";
	public String IconeMonstre4 = "lizimaj/monster_4.png";


    public Pannel() {
    	
       try {                
          image = ImageIO.read(new File(IconeJoueur));
       } catch (IOException ex) {
     
       }
       
       this.setFocusable(true);
       this.requestFocusInWindow();
       this.addKeyListener(new Clavier());
       
      
    }
    

    public void setIconeJoueur(String IconeJoueur) throws IOException {
		this.IconeJoueur = IconeJoueur;
		this.image = ImageIO.read(new File(IconeJoueur));
		this.image2 = ImageIO.read(new File(IconeMonstre1));

	}


	@Override
    protected void paintComponent(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	    int x1 = this.getWidth();
    	    int y1 = this.getHeight();    
    	    g2d.setColor(Color.BLACK);         
    	    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    	    super.paintComponent(g);
    	    g.drawImage(image, joueur.x,joueur.y , this); 
    	    g.drawImage(image2, Monster.x,Monster.y , this); 

    	    
    	  }       
    
    	}
   
   
  
    
 