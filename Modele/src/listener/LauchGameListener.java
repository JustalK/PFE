package listener;

import java.awt.event.ActionEvent;

import menu.Menu;

public class LauchGameListener extends Listener {

	public LauchGameListener(Menu fenetre) {
		super(fenetre);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.lauch();
	}
}
