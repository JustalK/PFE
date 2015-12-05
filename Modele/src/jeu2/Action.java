package jeu2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import menu.Menu;

public class Action extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private Jeu jeu2;
	private int position;
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
