package jeuChronometre;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Action extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private transient Jeu jeu2;
	private boolean value;
	
	public Action(Jeu jeu2,boolean value) {
		this.jeu2 = jeu2;
		this.value = value;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.jeu2.action(value);
	}

}
