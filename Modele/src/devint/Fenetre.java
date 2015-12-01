package devint;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import menu.Clavier;

public abstract class Fenetre extends JFrame {
	private Set<Integer> keyPressed;
    private Clavier clavier;
	
	public Fenetre() {
		// Enleve la barre de menu dans la JFrame - C'est moche sans !
	    this.setUndecorated(true);	
	   
        // Permet de mettre la fenetre en plein ecran
        this.setExtendedState(MAXIMIZED_BOTH);
        

        // Permet de creer le lecteur (listener - Ty english) de touche
        keyPressed  = new HashSet<Integer>();
        clavier = new Clavier(keyPressed);
        this.addKeyListener(clavier);
	}
}
