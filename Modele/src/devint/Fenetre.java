package devint;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.Moteur;

public class Fenetre extends JFrame implements Constantes, ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private Set<Integer> keyPressed;
    private Moteur moteur;
    private Clavier clavier;

    private boolean play;
    private boolean playMenu;
    
    public Fenetre() {
    	this.play = false;
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
		c.insets = new Insets(3,80,3,80);
		menu.setLayout(layoutMenu);

		JLabel menu0 = new JLabel("Modele de Jeu".toUpperCase(),JLabel.CENTER);
		menu0.setFont(new Font("Arial", Font.BOLD, 50));
		menu0.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;      
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
    	c.gridwidth = 3;		
    	menu.add(menu0, c);		
		
		JButton menu1 = new JButton("jouer".toUpperCase());
		menu1.setFont(new Font("Arial", Font.BOLD, 50));
		menu1.setForeground(Color.WHITE);
    	menu1.setBackground(Color.green);
    	menu1.setFocusPainted(false);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;   
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 1;
    	c.gridwidth = 3;		
    	menu.add(menu1, c);

		JButton menu2 = new JButton("Quitter".toUpperCase());
		menu2.addActionListener(this);
		
		menu2.setFont(new Font("Arial", Font.BOLD, 50));
		menu2.setForeground(Color.WHITE);
    	menu2.setBackground(Color.red);
    	menu2.setFocusPainted(false);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;  
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 2;
    	c.gridwidth = 3;		
    	menu.add(menu2, c);	
    	
    	this.add(menu);
        

        // Affiche la fenetre
        this.setVisible(true);

        this.playMenu = true;
        
        // Loop de jeu
        while(playMenu) {
        	if(play) {
	        	moteur = new Moteur(keyPressed);
	        	moteur.loop();
        	}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        this.playMenu = false;
        this.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
   
}
