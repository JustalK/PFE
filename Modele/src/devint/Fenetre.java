package devint;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import jeu.Moteur;

public class Fenetre extends JFrame implements Constantes {
    private static final long serialVersionUID = 1L;
    private Set<Integer> keyPressed;
    private Moteur moteur;
    private Clavier clavier;

    public Fenetre() {
    	// Enleve la barre de menu dans la JFrame
        this.setUndecorated(true);
        
        // Affiche la fenetre
        this.setVisible(true);
        
        // Permet d'agrandir la fenetre
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Utiliser la cle
        keyPressed  = new HashSet<Integer>();
        clavier = new Clavier(keyPressed);
        this.addKeyListener(clavier);
        
        // Loop de jeu
        moteur = new Moteur(keyPressed);
        moteur.loop();
        
        // Ferme la fenetre et le jeu
        this.dispose();
    }
   
}
