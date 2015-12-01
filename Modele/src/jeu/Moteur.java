package jeu;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import devint.Constantes;
import devint.Fenetre;

public class Moteur implements Constantes {
    private Set<Integer> keyPressed; 
	private boolean game;
	private JPanel play = new JPanel(); 
    private Fenetre fenetre;
	
    
    public Moteur(Set<Integer> keyPressed, Fenetre fenetre) {
    	this.keyPressed = keyPressed;
    	game = true;
    	this.fenetre = fenetre;
    }
    
    public void loop() {
    	init();
        while (game) {
        	update();
        	System.out.println(keyPressed);
        }
    }
    
    public void init() {
		GridBagLayout layoutMenu = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints(); 
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3,80,3,80);
		play.setLayout(layoutMenu);	
    	
		JLabel menu0 = new JLabel("azeaze".toUpperCase(),JLabel.CENTER);
		menu0.setFont(new Font("Arial", Font.BOLD, 50));
		menu0.setForeground(Color.RED);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;      
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 1;
    	c.gridwidth = 3;		
    	play.add(menu0, c);
    	
    	fenetre.add(play);

    	play.setVisible(true);
    }
    
    public void update() {
    	if(keyPressed.contains(KeyEvent.VK_ESCAPE)) {
    		game = !game;
    	}
    }
    
    public void render() {
    	
    }
}
