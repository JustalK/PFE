package devint;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import menu.Clavier;

public abstract class Fenetre extends JFrame {
	
	public Fenetre() {
		// Enleve la barre de menu dans la JFrame - C'est moche sans !
	    this.setUndecorated(true);	
	   
        // Permet de mettre la fenetre en plein ecran
        this.setExtendedState(MAXIMIZED_BOTH);
    	
        // Key Binding - Mieux qu'un keyListener car pas besoin du focus :)
    	this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("F5"), "F5");
    	this.getRootPane().getActionMap().put("F5",new F5Action());
    	addControl("F1",new F1Action());
    	addControl("F2",new F2Action());
    	addControl("F3",new F3Action());
    	addControl("F4",new F4Action());
    	addControl("F5",new F5Action());
    	addControl("ESCAPE",new EchapAction(this));
	}
	
	public void addControl(String key,Action action) {
    	this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key), key);
    	this.getRootPane().getActionMap().put(key,action);		
	}
}
