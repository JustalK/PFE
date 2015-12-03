package devint;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import t2s.SIVOXDevint;

public abstract class Fenetre extends JFrame {
    private SIVOXDevint sivox;
	
	public Fenetre() {
		// Enleve la barre de menu dans la JFrame - C'est moche sans !
	    this.setUndecorated(true);	
	   
        // Permet de mettre la fenetre en plein ecran
        this.setExtendedState(MAXIMIZED_BOTH);
    	
        // Key Binding - Mieux qu'un keyListener car pas besoin du focus :)
    	addControl("F1",new F1Action(this));
    	addControl("F2",new F2Action(this));
    	addControl("F3",new F3Action());
    	addControl("F4",new F4Action());
    	addControl("F5",new F5Action());
    	addControl("ESCAPE",new EchapAction(this));
    	
    	this.sivox = new SIVOXDevint(2);
	}
	
	public void addControl(String key,Action action) {
    	this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key), key);
    	this.getRootPane().getActionMap().put(key,action);		
	}
	
	public SIVOXDevint getSIVOX() {
		return sivox;
	}
}
