package devint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jeu.Moteur;

public class Fenetre extends JFrame implements Constantes {
    private static final long serialVersionUID = 1L;
    private Set<Integer> keyPressed;
    private Moteur moteur;
    private Clavier clavier;

    private boolean play = false;
    
    public Fenetre() {
    	// Enleve la barre de menu dans la JFrame
        this.setUndecorated(true);
        
        // Permet d'agrandir la fenetre
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Utiliser la cle
        keyPressed  = new HashSet<Integer>();
        clavier = new Clavier(keyPressed);
        this.addKeyListener(clavier);
        JPanel menu = new JPanel();
    	menu.setBackground(Color.black);
    	

		GridBagLayout layoutMenu = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3,3,3,3);
		menu.setLayout(layoutMenu);

		JPanel menu0 = new JPanel();
    	menu0.setBackground(Color.green);
    	menu0.setLayout(new BorderLayout());
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;      
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
    	c.gridwidth = 3;		
    	menu.add(menu0, c);		
		
		JPanel menu1 = new JPanel();
    	menu1.setBackground(Color.green);
    	menu1.setLayout(new BorderLayout());
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;   
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 1;
    	c.gridwidth = 3;		
    	menu.add(menu1, c);
		
		JPanel menu2 = new JPanel();
    	menu2.setBackground(Color.red);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;  
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 2;
    	c.gridwidth = 3;		
    	menu.add(menu2, c);
		
    	this.add(menu);
    	this.setContentPane(menu);
        

        // Affiche la fenetre
        this.setVisible(true);
        
        // Loop de jeu
        //while(true) {
        	if(play) {
	        	moteur = new Moteur(keyPressed);
	        	moteur.loop();
        	}
        	
        //}
        
        // Ferme la fenetre et le jeu
        //this.dispose();
    }
   
}
