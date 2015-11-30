package devint;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public interface Constantes {
    int LONGUEUR=1280;
    int HAUTEUR=720;
    int MS_PER_UPDATE=16;
    
    int MAX_GRAVITE=16;
    int MIN_GRAVITE=0;
    int VITESSE_JUMP = 5;
    int VITESSE_RUN = 10;
    int VITESSE_PREDASH = 10;
    int VITESSE_DASH = 40;
    
    // Ressources
    Icon CARRE1 = new ImageIcon("./ressources/carre1.jpg");
    Icon CARRE1_1 = new ImageIcon("./ressources/carre1_1.jpg");
    
    Icon CARRE2 = new ImageIcon("./ressources/carre2.jpg");
    
    Icon BACKGROUND = new ImageIcon("./ressources/background.jpg");
    
    Icon PLATEFORME_1 = new ImageIcon("./ressources/plateforme1.jpg");
    Icon PLATEFORME_2 = new ImageIcon("./ressources/plateforme2.jpg");
    
    int TAILLEX = BACKGROUND.getIconWidth();
    int TAILLEY = BACKGROUND.getIconWidth();
}
