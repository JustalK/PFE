package dvt.jeumultijoueur;

public final class ConstantesJeu {
    // POUR LES SI3 : Le System.getProperty("line.separator") ou \n ne
    // fonctionne pas dans un JLabel
    // Mais il faut retenir que le JLabel peut lire du HTML :) Trick powaa !
    public static final String CONSIGNE = "<html>" + "<center>ATTRAPER LE PETIT CARRE<br />"
            + "Joueur 1 : Touches Directionnelles<br />"
            + "Joueur 2 : Touches ZQSD<br /><br />"
            + "Appuyer sur ESPACE pour commencer</center></html>";
    public static final String WIN = "<html><center>UN JOUEUR A GAGNE<br /><br /><br /><br />"
            + "Appuyer sur espace pour recommencer</center></html>";
    public static final int TAILLE_X_MONSTER = 50;
    public static final int TAILLE_Y_MONSTER = 50;

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;
    
    private ConstantesJeu() {
        throw new AssertionError();
    }
}