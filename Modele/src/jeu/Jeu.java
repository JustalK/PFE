package jeu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

import menu.ConstantesMenu;
import devint.Fenetre;

public class Jeu extends Fenetre implements ConstantesMenu {
	private static final long serialVersionUID = 1L;
    private JPanel personnage;
	private JPanel world;
    private int x;
    private int y;
    
    private boolean[] button = {false,false,false,false};
	
    public Jeu() {
        init();
        this.setVisible(true);
        this.setFocusable(true);
        loop();
	}

	public void loop() {
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 60;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; 
	   long lastFpsTime = 0L;
	   int fps = 0;
	   
        while (this.isDisplayable()) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            
            lastFpsTime += updateLength;
            fps++;
            
            if (lastFpsTime >= 1000000000)
            {
               //System.out.println("(FPS: "+fps+")");
               lastFpsTime = 0;
               fps = 0;
            }
            
        	update();
        	render();
        	
        	try {
				Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
    
    public void init() {
    	addControlDown(KeyEvent.VK_DOWN,new Action(this,0,true));
    	addControlUp(KeyEvent.VK_DOWN,new Action(this,0,false));
    	
    	addControlDown(KeyEvent.VK_UP,new Action(this,1,true));
    	addControlUp(KeyEvent.VK_UP,new Action(this,1,false));
    	
    	addControlDown(KeyEvent.VK_LEFT,new Action(this,2,true));
    	addControlUp(KeyEvent.VK_LEFT,new Action(this,2,false));
    	
    	addControlDown(KeyEvent.VK_RIGHT,new Action(this,3,true));
    	addControlUp(KeyEvent.VK_RIGHT,new Action(this,3,false));
    	
    	world = new JPanel();
    	world.setBackground(getForeground());
    	
    	personnage = new JPanel();
    	personnage.setBounds(x, y, 100, 100);
    	personnage.setBackground(getBackground());
    	world.setLayout(null);
    	world.add(personnage);
    	this.add(world);
    }
    
    public void update() {
    	if(button[0]) y+=5;
    	if(button[1]) y-=5;
    	if(button[2]) x-=5;
    	if(button[3]) x+=5;
    }
    
    public void render() {
		personnage.setBounds(x, y, 100, 100);
    	personnage.setBackground(getForeground());
    	world.setBackground(getBackground());
    }
    
	public void action(int position, boolean value) {
		button[position] = value;
	}
}
