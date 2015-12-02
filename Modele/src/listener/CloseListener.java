package listener;

import java.awt.event.ActionEvent;

import menu.Menu;

public class CloseListener extends Listener {

	public CloseListener(Menu fenetre) {
		super(fenetre);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.dispose();
	}
}
