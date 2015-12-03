package devint;

import java.awt.Font;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import t2s.SIVOXDevint;

public abstract class Fenetre extends JFrame implements ConstantesDevint {
	private static final long serialVersionUID = 1L;
	private SIVOXDevint sivox;
	private Font font;
    private java.awt.Color background;
    private java.awt.Color foreground;
    private java.awt.Color buttonSelectedBackground;
    private java.awt.Color buttonUnselectedBackground;
    private java.awt.Color buttonSelectedForeground;
    private java.awt.Color buttonUnselectedForeground;
    private int colorChoice;
    private int fontChoice;
    private int syntheseNiveauChoice;
	
	public Fenetre() {
		// Enleve la barre de menu dans la JFrame - C'est moche sans !
	    this.setUndecorated(true);	
	   
        // Permet de mettre la fenetre en plein ecran
        this.setExtendedState(MAXIMIZED_BOTH);
    	
        // Key Binding - Mieux qu'un keyListener car pas besoin du focus :)
    	addControl("F1",new F1Action(this));
    	addControl("F2",new F2Action(this));
    	addControl("F3",new F3Action(this));
    	addControl("F4",new F4Action(this));
    	addControl("F5",new F5Action(this));
    	addControl("ESCAPE",new EchapAction(this));
    	
    	// Synthese vocale
    	this.sivox = new SIVOXDevint(2);
    	this.sivox.playWav(ACCUEIL_SON);
  
    	this.syntheseNiveauChoice = 2;
    	
    	//
    	font = FONT_DEFAULT[0];
    	background = BACKGROUND_DEFAULT[0];
    	foreground = FOREGROUND_DEFAULT[0];
    	buttonSelectedBackground = BUTTON_BACKGROUND_SELECTED_DEFAULT[0];
    	buttonUnselectedBackground = BUTTON_BACKGROUND_UNSELECTED_DEFAULT[0];
    	buttonSelectedForeground = BUTTON_FOREGROUND_SELECTED_DEFAULT[0];
    	buttonUnselectedForeground = BUTTON_FOREGROUND_UNSELECTED_DEFAULT[0];
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
	
	public SIVOXDevint getSIVOX() {
		return sivox;
	}
	
	public int getSyntheseNiveau() {
		return syntheseNiveauChoice;
	}
	
	public void changeSyntheseNiveau() {
		syntheseNiveauChoice = ++syntheseNiveauChoice % NBR_SYNTHESE_NIVEAU;
		System.out.println(syntheseNiveauChoice);
	}
	
	public void changeFont() {
		font = FONT_DEFAULT[++fontChoice % FONT_DEFAULT.length];
	}
	
	public void changeColor() {
    	background = BACKGROUND_DEFAULT[++colorChoice % BACKGROUND_DEFAULT.length];
    	foreground = FOREGROUND_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
    	buttonSelectedBackground = BUTTON_BACKGROUND_SELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
    	buttonUnselectedBackground = BUTTON_BACKGROUND_UNSELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
    	buttonSelectedForeground = BUTTON_FOREGROUND_SELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
    	buttonUnselectedForeground = BUTTON_FOREGROUND_UNSELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
	}
}
