package devint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class F5Action extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private transient Fenetre fenetre;
	
	public F5Action(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetre.changeSyntheseNiveau();
	}

}
