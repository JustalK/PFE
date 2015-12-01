package devint;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.Moteur;

public class Fenetre extends JFrame implements Constantes {
    private static final long serialVersionUID = 1L;
    private Set<Integer> keyPressed;
    private Moteur moteur;
    private Clavier clavier;

    private boolean play;
    private boolean playMenu;
    private List<JButton> listeButton;
    
    private GridBagConstraints c = new GridBagConstraints(); 
    private JPanel menu = new JPanel(); 
    private int countMenu = 0;
    
    private CloseListener cl;
    
    public Fenetre() {
    	this.play = false;
    	// Enleve la barre de menu dans la JFrame
        this.setUndecorated(true);
        
        // Permet d'agrandir la fenetre
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Utiliser la cle
        keyPressed  = new HashSet<Integer>();
        clavier = new Clavier(keyPressed);
        
        listeButton = new ArrayList<JButton>();
        
        this.addKeyListener(clavier);
        //JPanel menu = new JPanel();
    	menu.setBackground(Color.black);
    	

		GridBagLayout layoutMenu = new GridBagLayout();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3,80,3,80);
		menu.setLayout(layoutMenu);	
    	
		addLabel(TITLE_GAME);
    	cl = new CloseListener(this);
    	addMenu("Jouer");
    	addMenu("Quitter");
    	
    	
    	this.add(menu);
        
        // Affiche la fenetre
        this.setVisible(true);

        this.playMenu = true;
        
        // Loop de jeu
        while(playMenu) {
        	if(play) {
            	menu.setVisible(false);
	        	moteur = new Moteur(keyPressed,this);
	        	moteur.loop();
	        	this.play = false;
            	menu.setVisible(true);
        	}
        }
    }

    private void addLabel(String title) {
		JLabel menu0 = new JLabel(title.toUpperCase(),JLabel.CENTER);
		menu0.setFont(new Font("Arial", Font.BOLD, 50));
		menu0.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;      
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = countMenu;
    	c.gridwidth = 3;		
    	menu.add(menu0, c);
    	
    	countMenu++;
    }
    
    private void addMenu(String title) {
    	if(title!=null) {
			JButton button = new JButton(title.toUpperCase());
			button.addActionListener(cl);
			
			button.setFont(new Font("Arial", Font.BOLD, 50));
			button.setForeground(Color.WHITE);
			button.setBackground(Color.red);
			button.setFocusPainted(false);
	        c.fill = GridBagConstraints.BOTH;
	        c.ipady = 100;  
	        c.weightx = 1.0;
	        c.weighty = 1.0;
	        c.gridx = 0;
	        c.gridy = countMenu;
	    	c.gridwidth = 3;		
	    	menu.add(button, c);
	    	
	    	listeButton.add(button);
	    	countMenu++;
    	}
    }
}
