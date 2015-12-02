package jeu;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import t2s.SIVOXDevint;
import menu.Clavier;
import menu.ConstantesMenu;
import menu.Menu;

public class Moteur extends JFrame implements ConstantesMenu {
    private Set<Integer> keyPressed; 
	private boolean game;
    private Clavier clavier;
    
    public Moteur() {
        this.setUndecorated(true);
        
        // Permet d'agrandir la fenetre
        this.setExtendedState(MAXIMIZED_BOTH);

        keyPressed  = new HashSet<Integer>();
        this.clavier = new Clavier(keyPressed);
        this.addKeyListener(clavier);
        this.setVisible(true);
        loop();
	}

	public void loop() {
		game = true;
        while (game) {
        	update();
        }
        this.dispose();
    }
    
    public void init() {
    	
    }
    
    public void update() {
    	if(keyPressed.contains(KeyEvent.VK_ESCAPE)) {
    		game = !game;
    	}
    }
    
    public void render() {
    	
    }
}
