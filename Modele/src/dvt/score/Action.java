package dvt.score;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Score score;
    private boolean value;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     * @param position La position dans l'array que l'on fera le changement
     * @param value La valeur que l'on souhaite modifie
     */    
    public Action(Score score, boolean value) {
        this.score = score;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(value) {
            //this.score.top();
        } else {
            //this.score.down();
        }
    }
}
