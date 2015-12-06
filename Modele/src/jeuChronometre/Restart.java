package jeuChronometre;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Restart extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private transient Jeu jeu2;
	
	public Restart(Jeu jeu2) {
		this.jeu2 = jeu2;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.jeu2.restart();
	}

}
