package dvt.devint;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import t2s.SIVOXDevint;
import static dvt.devint.ConstantesDevint.*;

/**
 * Permet de fixer l'ensemble des des methodes pour toutes les fenetres cree.
 * @author Justal Kevin
 */
public abstract class Fenetre extends JFrame {
    private static final long serialVersionUID = 1L;
    private transient SIVOXDevint sivox;
    private Font fontDefault;
    private java.awt.Color backgroundDefault;
    private java.awt.Color foregroundDefault;
    private java.awt.Color buttonSelectedBackground;
    private java.awt.Color buttonUnselectedBackground;
    private java.awt.Color buttonSelectedForeground;
    private java.awt.Color buttonUnselectedForeground;
    private int colorChoice;
    private int fontChoice;
    private int syntheseNiveauChoice;

    private static final String[] PHRASE_SYNTHESE_NIVEAU = {"Synthese maximale", "Synthese moyenne", "Synthese minimale" };
    private static final Font[] FONT_DEFAULT = {
            new Font(FONT_TYPE_DEFAULT, Font.BOLD, 50), new Font(FONT_TYPE_DEFAULT, Font.BOLD, 60),
            new Font(FONT_TYPE_DEFAULT, Font.BOLD, 70), new Font(FONT_TYPE_DEFAULT, Font.BOLD, 90),
            new Font(FONT_TYPE_DEFAULT, Font.BOLD, 40) };
    private static final java.awt.Color[] BACKGROUND_DEFAULT = {new Color(155, 215, 202), new Color(255, 0, 203),new Color(0, 255, 0) };
    private static final java.awt.Color[] FOREGROUND_DEFAULT = { Color.BLACK,new Color(0, 0, 255), new Color(255, 255, 0) };
    private static final java.awt.Color[] BUTTON_BACKGROUND_SELECTED_DEFAULT = {Color.BLACK, new Color(0, 0, 255), new Color(255, 0, 0) };
    private static final java.awt.Color[] BUTTON_BACKGROUND_UNSELECTED_DEFAULT = {Color.WHITE, Color.WHITE, new Color(0, 255, 255) };
    private static final java.awt.Color[] BUTTON_FOREGROUND_SELECTED_DEFAULT = {Color.WHITE, Color.WHITE, Color.WHITE };
    private static final java.awt.Color[] BUTTON_FOREGROUND_UNSELECTED_DEFAULT = {Color.BLACK, Color.BLACK, Color.BLACK };

    /**
     * Permet de creer l'objet fenetre avec tout les choix par defaut
     * @author Justal Kevin
     */
    public Fenetre() {
        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);

        // Key Binding - Mieux qu'un keyListener car pas besoin du focus :)
        addControl("F1", new F1Action(this));
        addControl("F2", new F2Action(this));
        addControl("F3", new F3Action(this));
        addControl("F4", new F4Action(this));
        addControl("F5", new F5Action(this));
        addControl("ESCAPE", new EchapAction(this));

        this.sivox = new SIVOXDevint(2);

        this.syntheseNiveauChoice = 2;

        fontDefault = FONT_DEFAULT[0];
        backgroundDefault = BACKGROUND_DEFAULT[0];
        foregroundDefault = FOREGROUND_DEFAULT[0];
        buttonSelectedBackground = BUTTON_BACKGROUND_SELECTED_DEFAULT[0];
        buttonUnselectedBackground = BUTTON_BACKGROUND_UNSELECTED_DEFAULT[0];
        buttonSelectedForeground = BUTTON_FOREGROUND_SELECTED_DEFAULT[0];
        buttonUnselectedForeground = BUTTON_FOREGROUND_UNSELECTED_DEFAULT[0];
    }

    /**
     * Permet d'ajouter une touche de controle a la fenetre sans tenir compte de l'etat de la touche (appuye ou release).
     * @param key La touche passer par un string
     * @param action L'action que l'on realisera lors de l'appuie sur la touche
     * @author Justal Kevin
     */
    public void addControl(String key, Action action) {
        this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key), key);
        this.getRootPane().getActionMap().put(key, action);
    }

    /**
     * Permet d'ajouter une action sur une touche lors de l'appuie sur cette derniere
     * @param key La touche que l'on utilise suivant le KeyEvent
     * @param action L'action que l'on realisera lors de l'appuie sur la touche
     * @author Justal Kevin
     */
    public void addControlDown(int key, Action action) {
        this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key, 0, false), key + "Down");
        this.getRootPane().getActionMap().put(key + "Down", action);
    }

    /**
     * Permet d'ajouter action sur une touche lorsque l'on arrete d'appuyer sur cette derniere
     * @param key La touche que l'on utilise suivant le keyEvent
     * @param action
     * @author Justal Kevin
     */
    public void addControlUp(int key, Action action) {
        this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key, 0, true), key + "Up");
        this.getRootPane().getActionMap().put(key + "Up", action);
    }

    @Override
    public Font getFont() {
        return fontDefault;
    }

    @Override
    public java.awt.Color getBackground() {
        return backgroundDefault;
    }

    @Override
    public java.awt.Color getForeground() {
        return foregroundDefault;
    }

    /**
     * Permet de retourner la couleur de fond par defaut des boutons selectionner
     * @return Retourne la couleur de fond par defaut des boutons selectionner
     * @author Justal Kevin
     */
    public java.awt.Color getButtonSelectedBackground() {
        return buttonSelectedBackground;
    }

    /**
     * Permet de retourner la couleur de fond par defaut des boutons non selectionner
     * @return Retourne la couleur de fond par defaut des boutons non selectionner
     * @author Justal Kevin
     */
    public java.awt.Color getButtonUnselectedBackground() {
        return buttonUnselectedBackground;
    }

    /**
     * Permet de retourner la couleur du texte par defaut sur les boutons selectionner
     * @return Retourne la couleur du texte par defaut sur les boutons selectionner
     * @author Justal Kevin
     */
    public java.awt.Color getButtonSelectedForeground() {
        return buttonSelectedForeground;
    }

    /**
     * Permet de retourner la couleur du texte par defaut sur les boutons non selectionner
     * @return Retourner la couleur du texte par defaut sur les boutons non selectionner
     * @author Justal Kevin
     */
    public java.awt.Color getButtonUnselectedForeground() {
        return buttonUnselectedForeground;
    }

    /**
     * Permet de retourner la voix de Synthese utilise pour l'ensemble du projet
     * @return La voix de synthese
     * @author Justal Kevin
     */
    public SIVOXDevint getSIVOX() {
        return sivox;
    }

    /**
     * Permet de retourner le niveau de synthese
     * @return Le niveau de synthese
     * @author Justal Kevin
     */
    public int getSyntheseNiveau() {
        return syntheseNiveauChoice;
    }

    /**
     * Permet de changer le niveau de la synthese
     * @author Justal Kevin
     */
    public void changeSyntheseNiveau() {
        syntheseNiveauChoice = ++syntheseNiveauChoice % NBR_SYNTHESE_NIVEAU;
        this.sivox.playText(PHRASE_SYNTHESE_NIVEAU[syntheseNiveauChoice]);
        this.sivox.setSyntheseNiveau(syntheseNiveauChoice);
    }

    /**
     * Permet de changer le font par defaut de la fenetre
     * @author Justal Kevin
     */
    public void changeFont() {
        fontDefault = FONT_DEFAULT[++fontChoice % FONT_DEFAULT.length];
    }

    /**
     * Permet de changer la couleur par defaut de l'ensemble des elements
     * @author Justal Kevin
     */
    public void changeColor() {
        backgroundDefault = BACKGROUND_DEFAULT[++colorChoice % BACKGROUND_DEFAULT.length];
        foregroundDefault = FOREGROUND_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
        buttonSelectedBackground = BUTTON_BACKGROUND_SELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
        buttonUnselectedBackground = BUTTON_BACKGROUND_UNSELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
        buttonSelectedForeground = BUTTON_FOREGROUND_SELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
        buttonUnselectedForeground = BUTTON_FOREGROUND_UNSELECTED_DEFAULT[colorChoice % BACKGROUND_DEFAULT.length];
    }
}
