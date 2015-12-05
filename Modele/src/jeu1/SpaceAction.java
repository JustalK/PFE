package jeu1;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class SpaceAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private Jeu jeu1;
	
	public SpaceAction(Jeu jeu1) {
		this.jeu1 = jeu1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.jeu1.lauch();
	}
}
