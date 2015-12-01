package menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import devint.Fenetre;
import jeu.Moteur;
import listener.CloseListener;
import listener.LauchGameListener;

public class Menu extends Fenetre implements ConstantesMenu {
    private static final long serialVersionUID = 1L;
    private boolean play;
    private boolean playMenu;
    
    private GridBagConstraints c = new GridBagConstraints(); 
    private JPanel menu = new JPanel(); 
    private int countMenu = 0;
    
    public Menu() {
    	this.play = false;
    	menu.setBackground(BACKGROUND_COLOR);
    	

		GridBagLayout layoutMenu = new GridBagLayout();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(MARGE_TOP_BOT,MARGE_LEFT_RIGHT,MARGE_TOP_BOT,MARGE_LEFT_RIGHT);
		menu.setLayout(layoutMenu);	
    	
		addLabel(TITLE_GAME);
        this.playMenu = true;
    	addMenu("Jouer",new LauchGameListener(this));
    	addMenu("Quitter",new CloseListener(this));
    	
    	this.add(menu);
        
        // Affiche la fenetre
        this.setVisible(true);
    }

    public void loop() {
        while(playMenu) {
        	if(play) {
            	menu.setVisible(false);
            	new Moteur();
	        	this.play = false;
            	menu.setVisible(true);
        	}
        }   	
    }
    
    private void addLabel(String title) {
		JLabel menu0 = new JLabel(title.toUpperCase(),JLabel.CENTER);
		menu0.setFont(new Font("Arial", Font.BOLD, TAILLE));
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
    
    private void addMenu(String title,ActionListener action) {
    	if(title!=null) {
			JButton button = new JButton(title.toUpperCase());
			button.addActionListener(action);
			
			button.setFont(new Font("Arial", Font.BOLD, TAILLE));
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
	    	countMenu++;
    	}
    }
    
    public void close() {
    	this.playMenu = false;
    	this.dispose();
    }

	public void lauch() {
    	this.play = true;
	}
}
