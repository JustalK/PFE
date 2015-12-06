package dvt.devint;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import t2s.SIVOXDevint;
import static dvt.devint.ConstantesDevint.*;

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

    private static final String[] PHRASE_SYNTHESE_NIVEAU = {
            "Synthese maximale", "Synthese moyenne", "Synthese minimale" };
    private static final Font[] FONT_DEFAULT = {
            new Font(FONT_TYPE_DEFAULT, Font.BOLD, 50), new Font(FONT_TYPE_DEFAULT, Font.BOLD, 60),
            new Font(FONT_TYPE_DEFAULT, Font.BOLD, 70), new Font(FONT_TYPE_DEFAULT, Font.BOLD, 90),
            new Font(FONT_TYPE_DEFAULT, Font.BOLD, 40) };
    private static final java.awt.Color[] BACKGROUND_DEFAULT = {
            new Color(155, 215, 202), new Color(255, 0, 203),
            new Color(0, 255, 0) };
    private static final java.awt.Color[] FOREGROUND_DEFAULT = { Color.BLACK,
            new Color(0, 0, 255), new Color(255, 255, 0) };
    private static final java.awt.Color[] BUTTON_BACKGROUND_SELECTED_DEFAULT = {
            Color.BLACK, new Color(0, 0, 255), new Color(255, 0, 0) };
    private static final java.awt.Color[] BUTTON_BACKGROUND_UNSELECTED_DEFAULT = {
            Color.WHITE, Color.WHITE, new Color(0, 255, 255) };
    private static final java.awt.Color[] BUTTON_FOREGROUND_SELECTED_DEFAULT = {
            Color.WHITE, Color.WHITE, Color.WHITE };
    private static final java.awt.Color[] BUTTON_FOREGROUND_UNSELECTED_DEFAULT = {
            Color.BLACK, Color.BLACK, Color.BLACK };

    public Fenetre() {
        // Enleve la barre de menu dans la JFrame - C'est moche sans !
        this.setUndecorated(true);

        // Permet de mettre la fenetre en plein ecran
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

    public void addControl(String key, Action action) {
        this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(key), key);
        this.getRootPane().getActionMap().put(key, action);
    }

    public void addControlDown(int key, Action action) {
        this.getRootPane().getInputMap()
                .put(KeyStroke.getKeyStroke(key, 0, false), key + "Down");
        this.getRootPane().getActionMap().put(key + "Down", action);
    }

    public void addControlUp(int key, Action action) {
        this.getRootPane().getInputMap()
                .put(KeyStroke.getKeyStroke(key, 0, true), key + "Up");
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

    public java.awt.Color getButtonSelectedBackground() {
        return buttonSelectedBackground;
    }

    public java.awt.Color getButtonUnselectedBackground() {
        return buttonUnselectedBackground;
    }

    public java.awt.Color getButtonSelectedForeground() {
        return buttonSelectedForeground;
    }

    public java.awt.Color getButtonUnselectedForeground() {
        return buttonUnselectedForeground;
    }

    public SIVOXDevint getSIVOX() {
        return sivox;
    }

    public int getSyntheseNiveau() {
        return syntheseNiveauChoice;
    }

    public void changeSyntheseNiveau() {
        syntheseNiveauChoice = ++syntheseNiveauChoice % NBR_SYNTHESE_NIVEAU;
        this.sivox.playText(PHRASE_SYNTHESE_NIVEAU[syntheseNiveauChoice]);
        this.sivox.setSyntheseNiveau(syntheseNiveauChoice);
    }

    public void changeFont() {
        fontDefault = FONT_DEFAULT[++fontChoice % FONT_DEFAULT.length];
    }

    public void changeColor() {
        backgroundDefault = BACKGROUND_DEFAULT[++colorChoice
                % BACKGROUND_DEFAULT.length];
        foregroundDefault = FOREGROUND_DEFAULT[colorChoice
                % BACKGROUND_DEFAULT.length];
        buttonSelectedBackground = BUTTON_BACKGROUND_SELECTED_DEFAULT[colorChoice
                % BACKGROUND_DEFAULT.length];
        buttonUnselectedBackground = BUTTON_BACKGROUND_UNSELECTED_DEFAULT[colorChoice
                % BACKGROUND_DEFAULT.length];
        buttonSelectedForeground = BUTTON_FOREGROUND_SELECTED_DEFAULT[colorChoice
                % BACKGROUND_DEFAULT.length];
        buttonUnselectedForeground = BUTTON_FOREGROUND_UNSELECTED_DEFAULT[colorChoice
                % BACKGROUND_DEFAULT.length];
    }
}
