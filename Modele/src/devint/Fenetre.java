package devint;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import listener.SpaceAction;
import menu.Clavier;

public abstract class Fenetre extends JFrame {
	
	public Fenetre() {
		// Enleve la barre de menu dans la JFrame - C'est moche sans !
	    this.setUndecorated(true);	
	   
        // Permet de mettre la fenetre en plein ecran
        this.setExtendedState(MAXIMIZED_BOTH);
        
    	this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("F2"), "myAction");
    	this.getRootPane().getActionMap().put("myAction",new SpaceAction());
        // Permet de creer le lecteur (listener - Ty english) de touche
        //keyPressed  = new HashSet<Integer>();
        //clavier = new Clavier(keyPressed);
        //this.addKeyListener(clavier);
	}
}
