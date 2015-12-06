package dvt.jeumultijoueur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Jeu jeu1;
    private int position;
    private boolean value;

    public Action(Jeu jeu1, int position, boolean value) {
        this.jeu1 = jeu1;
        this.position = position;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu1.action(position, value);
    }

}
