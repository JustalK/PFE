package dvt.jeuchronometre;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static dvt.jeuchronometre.ConstantesJeu.*;
import dvt.devint.Fenetre;
import dvt.jeuchronometre.Action;

/**
 * Permet de gerer le jeu et la fenetre qui contient le jeu
 * @author Justal Kevin
 */
public class Jeu extends Fenetre {
    private static final long serialVersionUID = 1L;
    private Chronometer ch;
    private JPanel world;
    private JLabel info;
    private int count;
    private boolean pressed;
    private int record;

    /**
     * Permet de creer et initialiser la fenetre de jeu
     */
    public Jeu() {
        init();
        this.setVisible(true);
        this.setFocusable(true);
    }

    /**
     * L'initalisation du jeu
     */
    public void init() {
        world = new JPanel();
        world.setBackground(getForeground());
        world.setLayout(null);

        addControlDown(KeyEvent.VK_SPACE, new Action(this, true));
        addControlUp(KeyEvent.VK_SPACE, new Action(this, false));
        addControlDown(KeyEvent.VK_ENTER, new Restart(this));

        info = new JLabel(CONSIGNE, JLabel.CENTER);
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);

        this.add(world);
    }

    /**
     * La loop du jeu qui permet de garder un FPS (frame per seconds) constant peu importe le PC
     */
    public void loop() {
        long lastLoopTime,timeLoop;

        reset();
        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            
            update();
            render();

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

    /**
     * Permet de reset le jeu afin de recommencer une partie
     */
    private void reset() {
        count = 0;
        ch = new Chronometer();
        this.pressed = true;
        info.setText(CONSIGNE);
    }

    /**
     * Permet de faire l'update du jeu
     */
    private void update() {
        if (count > 0) {
            ch.stop();
            int seconds = (int) ch.getSeconds();
            if (seconds < 10) {
                info.setText("<html><center>" + ch.getChrono() + "<br /><br />"
                        + count + "</center></html>");
                record = count;
            } else {
                info.setText("<html><center>BRAVO<br />Vous avez appuye "
                        + record
                        + " fois sur espace<br /><br /><br />Pour recommencer, appuyez sur 'Entree'</center></html>");
            }
        }
    }

    /**
     * Permet de faire le rendue du jeu suivant les modifications faites dans l'update
     */
    private void render() {
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        world.setBackground(getBackground());
    }    
    
    /**
     * Permet de gerer les resultats des actions
     * @param value La valeur que l'action nous renvoie
     */
    public void action(boolean value) {
        if (pressed) {
            if (count == 0)
                ch.start();
            count++;
            pressed = false;
        }
        if (!value)
            pressed = true;
    }

    /**
     * Permet de gerer l'action pour redemarrer le jeu
     */
    public void restart() {
        reset();
    }
}
