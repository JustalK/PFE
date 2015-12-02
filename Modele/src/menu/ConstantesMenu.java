package menu;
import java.awt.Color;

public interface ConstantesMenu {
	// Titre du jeu
	String TITLE_GAME="Modele de Jeu";
	
	java.awt.Color BACKGROUND_COLOR = new Color(155,215,202);
	
	// Style du titre du menu
	String FONT_TYPE_TITLE="Arial";
	int FONT_TYPE_SIZE_TITLE = 50;
	java.awt.Color FOREGROUND_TITLE = Color.BLACK;
	
	// Style du menu pour les boutons selectionne
	String FONT_TYPE_SELECTED_DEFAULT="Arial";
	int TAILLE_SELECTED_DEFAULT = 70;
	java.awt.Color BORDURE_SELECTED_DEFAULT = Color.BLACK;
	int BORDURE_SIZE_SELECTED_DEFAULT = 12;
	java.awt.Color BACKGROUND_SELECTED_DEFAULT = Color.WHITE;
	java.awt.Color FOREGROUND_SELECTED_DEFAULT = Color.BLACK;
	
	// Style du menu pour les boutons non selectionne
	String FONT_TYPE_UNSELECTED_DEFAULT="Arial";
	int TAILLE_UNSELECTED_DEFAULT = 50;
	java.awt.Color BORDURE_UNSELECTED_DEFAULT = Color.WHITE;
	int BORDURE_SIZE_UNSELECTED_DEFAULT = 10;
	java.awt.Color BACKGROUND_UNSELECTED_DEFAULT = Color.BLACK;
	java.awt.Color FOREGROUND_UNSELECTED_DEFAULT = Color.WHITE;
	
	// 
	int MARGE_LEFT_RIGHT = 80;
	int MARGE_TOP_BOT = 3;
}
