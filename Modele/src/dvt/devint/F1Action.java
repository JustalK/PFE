package dvt.devint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import static dvt.devint.ConstantesDevint.*;

public class F1Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Fenetre fenetre;

    public F1Action(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.getSIVOX().playWav(F1_SON);
    }

}
