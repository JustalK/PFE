package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menu.Menu;

public abstract class Listener implements ActionListener {
	protected Menu fenetre;
	
	public Listener(Menu fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* NOTHING */
	}
}
