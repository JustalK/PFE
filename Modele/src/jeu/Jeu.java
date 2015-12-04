package jeu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

import menu.ConstantesMenu;
import devint.Fenetre;

public class Jeu extends Fenetre implements ConstantesMenu {
	private static final long serialVersionUID = 1L;
    private JPanel personnage1;
    private JPanel personnage2;
    private JPanel monster;
	private JPanel world;
    private int xPlayer1;
    private int yPlayer1;
    private int xPlayer2;
    private int yPlayer2;
    private int xMonster;
    private int yMonster;
    
    private JLabel info;
    
    //
    private boolean[] controlPlayers = {false,false,false,false,false,false,false,false};
	
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
	   
	   reset();
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
    	world = new JPanel();
    	world.setBackground(getForeground());
    	world.setLayout(null);
    	
    	// Player 1
    	addControlDown(KeyEvent.VK_DOWN,new Action(this,0,true));
    	addControlUp(KeyEvent.VK_DOWN,new Action(this,0,false));
    	
    	addControlDown(KeyEvent.VK_UP,new Action(this,1,true));
    	addControlUp(KeyEvent.VK_UP,new Action(this,1,false));
    	
    	addControlDown(KeyEvent.VK_LEFT,new Action(this,2,true));
    	addControlUp(KeyEvent.VK_LEFT,new Action(this,2,false));
    	
    	addControlDown(KeyEvent.VK_RIGHT,new Action(this,3,true));
    	addControlUp(KeyEvent.VK_RIGHT,new Action(this,3,false));
    	
    	personnage1 = new JPanel();
    	personnage1.setBounds(xPlayer1, yPlayer1, 100, 100);
    	personnage1.setBackground(getBackground());
    	world.add(personnage1);
    	
    	//Player2
    	addControlDown(KeyEvent.VK_S,new Action(this,4,true));
    	addControlUp(KeyEvent.VK_S,new Action(this,4,false));
    	
    	addControlDown(KeyEvent.VK_Z,new Action(this,5,true));
    	addControlUp(KeyEvent.VK_Z,new Action(this,5,false));
    	
    	addControlDown(KeyEvent.VK_Q,new Action(this,6,true));
    	addControlUp(KeyEvent.VK_Q,new Action(this,6,false));
    	
    	addControlDown(KeyEvent.VK_D,new Action(this,7,true));
    	addControlUp(KeyEvent.VK_D,new Action(this,7,false));
    	
    	personnage2 = new JPanel();
    	personnage2.setBounds(xPlayer2, yPlayer2, 100, 100);
    	personnage2.setBackground(getBackground());
    	world.add(personnage2);    	
    	
    	// Monster
    	monster = new JPanel();
    	monster.setBounds(xMonster, yMonster, 50, 50);
    	monster.setBackground(getBackground());
    	world.add(monster);       	
    	
    	this.add(world);
    }
    
    public void update() {
    	if(controlPlayers[0]) yPlayer1 = yPlayer1+5 > this.getHeight()-personnage1.getHeight() ? yPlayer1 : yPlayer1+5;
    	if(controlPlayers[1]) yPlayer1 = yPlayer1-5 < 0 ? 0 : yPlayer1-5;
    	if(controlPlayers[2]) xPlayer1 = xPlayer1-5 < 0 ? 0 : xPlayer1-5;
    	if(controlPlayers[3]) xPlayer1 = xPlayer1+5 > this.getWidth()-personnage1.getWidth() ? xPlayer1 : xPlayer1+5;
    	
    	if(controlPlayers[4]) yPlayer2 = yPlayer2+5 > this.getHeight()-personnage2.getHeight() ? yPlayer2 : yPlayer2+5;
    	if(controlPlayers[5]) yPlayer2 = yPlayer2-5 < 0 ? 0 : yPlayer2-5;
    	if(controlPlayers[6]) xPlayer2 = xPlayer2-5 < 0 ? 0 : xPlayer2-5;
    	if(controlPlayers[7]) xPlayer2 = xPlayer2+5 > this.getWidth()-personnage2.getWidth() ? xPlayer2 : xPlayer2+5;
    }
    
    public void render() {
		personnage1.setBounds(xPlayer1, yPlayer1, 100, 100);
		personnage2.setBounds(xPlayer2, yPlayer2, 100, 100);
		monster.setBounds(xMonster, yMonster, 50, 50);
    	personnage1.setBackground(getForeground());
    	personnage2.setBackground(getForeground());
    	monster.setBackground(getForeground());
    	world.setBackground(getBackground());
    }
    
    public void reset() {
   		xPlayer2 = this.getWidth()-100;
   		yPlayer2 = this.getHeight()-100;
   		xMonster = (this.getWidth()-50)/2;
   		yMonster = (this.getHeight()-50)/2;
    }
    
	public void action(int position, boolean value) {
		controlPlayers[position] = value;
	}
}
