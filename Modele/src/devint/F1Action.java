package devint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class F1Action extends AbstractAction implements ConstantesDevint {
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	
	public F1Action(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetre.getSIVOX().playWav(F1_SON);
	}

}
