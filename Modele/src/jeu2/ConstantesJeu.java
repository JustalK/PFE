package jeu2;

public interface ConstantesJeu {
	// POUR LES SI3 : Le System.getProperty("line.separator") ou \n ne fonctionne pas dans un JLabel
	// Mais il faut retenir que le JLabel peut lire du HTML :) Trick powaa !
	String CONSIGNE = "<html>"
			+ "<center>APPUYER LE PLUS DE FOIS SUR ESPACE<br />"
			+ "Vous avez 10 secondes pour faire le meilleur score<br /><br />"
			+ "Le jeu commencera des que vous appuyerez sur espace</center></html>";
}
