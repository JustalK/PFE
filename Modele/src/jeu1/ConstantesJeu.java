package jeu1;

public interface ConstantesJeu {
	// POUR LES SI3 : Le System.getProperty("line.separator") ou \n ne fonctionne pas dans un JLabel
	// Mais il faut retenir que le JLabel peut lire du HTML :) Trick powaa !
	String CONSIGNE = "<html>"
			+ "<center>ATTRAPER LE PETIT CARRE<br />"
			+ "Joueur 1 : Touches Directionnelles<br />"
			+ "Joueur 2 : Touches ZQSD<br /><br />"
			+ "Appuyer sur ESPACE pour commencer</center></html>";
	String WIN = "<html><center>UN JOUEUR A GAGNE<br /><br /><br /><br />"
			+ "Appuyer sur espace pour recommencer</center></html>";
	int TAILLE_X_MONSTER = 50;
	int TAILLE_Y_MONSTER = 50;
}
