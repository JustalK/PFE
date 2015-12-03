package devint;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import t2s.SIVOXDevint;

public abstract class Fenetre extends JFrame implements ConstantesDevint {
    private SIVOXDevint sivox;
	private Font font;
    private java.awt.Color background;
    private java.awt.Color foreground;
    private java.awt.Color buttonSelectedBackground;
    private java.awt.Color buttonUnselectedBackground;
    private java.awt.Color buttonSelectedForeground;
    private java.awt.Color buttonUnselectedForeground;
	
	public Fenetre() {
		// Enleve la barre de menu dans la JFrame - C'est moche sans !
	    this.setUndecorated(true);	
	   
        // Permet de mettre la fenetre en plein ecran
        this.setExtendedState(MAXIMIZED_BOTH);
    	
        // Key Binding - Mieux qu'un keyListener car pas besoin du focus :)
    	addControl("F1",new F1Action(this));
    	addControl("F2",new F2Action(this));
    	addControl("F3",new F3Action(this));
    	addControl("F4",new F4Action());
    	addControl("F5",new F5Action());
    	addControl("ESCAPE",new EchapAction(this));
    	
    	// Synthese vocale
    	this.sivox = new SIVOXDevint(2);
    	this.sivox.playWav(ACCUEIL_SON);
    	
    	//
    	font = new Font(FONT_TYPE_DEFAULT, Font.BOLD, TAILLE_DEFAULT);
    	
    	//
    	background = BACKGROUND_DEFAULT;
    	foreground = FOREGROUND_DEFAULT;
    	buttonSelectedBackground = BUTTON_BACKGROUND_SELECTED_DEFAULT;
    	buttonUnselectedBackground = BUTTON_BACKGROUND_UNSELECTED_DEFAULT;
    	buttonSelectedForeground = BUTTON_FOREGROUND_SELECTED_DEFAULT;
    	buttonUnselectedForeground = BUTTON_FOREGROUND_UNSELECTED_DEFAULT;
	}
	
	public void addControl(String key,Action action) {
    	this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key), key);
    	this.getRootPane().getActionMap().put(key,action);		
	}
	
	public Font getFont() {
		return font;
	}
	
	public java.awt.Color getBackground() {
		return background;
	}

	public java.awt.Color getForeground() {
		return foreground;
	}
	
	public java.awt.Color getButtonSelectedBackground() {
		return buttonSelectedBackground;
	}

	public java.awt.Color getButtonUnselectedBackground() {
		return buttonUnselectedBackground;
	}	

	public java.awt.Color getButtonSelectedForeground() {
		return buttonSelectedForeground;
	}

	public java.awt.Color getButtonUnselectedForeground() {
		return buttonUnselectedForeground;
	}	
	
	public void changeColor() {
    	background = FOREGROUND_DEFAULT;
    	foreground = BACKGROUND_DEFAULT;
    	buttonSelectedBackground = BACKGROUND_DEFAULT;
    	buttonUnselectedBackground = BACKGROUND_DEFAULT;
    	buttonSelectedForeground = BACKGROUND_DEFAULT;
    	buttonUnselectedForeground = BACKGROUND_DEFAULT;
    	this.validate();
    	this.revalidate();
    	this.repaint();
	}
	
	public SIVOXDevint getSIVOX() {
		return sivox;
	}
}
