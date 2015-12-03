package menu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class DownAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private Menu fenetre;
	
	public DownAction(Menu fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetre.down();
	}

}
