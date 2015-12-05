package jeu2;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu2.ConstantesJeu;
import devint.Fenetre;

public class Jeu extends Fenetre implements ConstantesJeu {
	private static final long serialVersionUID = 1L;

	private JPanel world;
    private JLabel info;
    
    private boolean play;
	
    public Jeu() {
        init();
        this.setVisible(true);
        this.setFocusable(true);
	}
    
    public void init() {
    	world = new JPanel();
    	world.setBackground(getForeground());
    	world.setLayout(null); 
    	
   		info = new JLabel(CONSIGNE,JLabel.CENTER);
   		info.setFont(getFont());
		info.setVisible(true);
   		world.add(info);
    	
    	this.add(world);
    }
    
	public void loop() {
		   long lastLoopTime = System.nanoTime();
		   final int TARGET_FPS = 60;
		   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; 
		   
		   reset();
	       while (this.isDisplayable()) {
	            long now = System.nanoTime();
	            lastLoopTime = now;
	            
	            if(play) {
	            	update();
	            }
	        	render();
	        	
	        	try {
					Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	    }

	private void reset() {
	}

	private void render() {
		info.setBounds(0, 0, this.getWidth(), this.getHeight());
    	world.setBackground(getBackground());
	}

	private void update() {
		
	}
}
