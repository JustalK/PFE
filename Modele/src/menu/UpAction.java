package menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import devint.Fenetre;

public class UpAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private Menu fenetre;
	
	public UpAction(Menu fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetre.up();
	}

}
