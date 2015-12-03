package devint;

import java.awt.Color;
import java.awt.Font;

interface ConstantesDevint {
	String F1_SON = "../ressources/sons/aideF1.wav";
	String F2_SON = "../ressources/sons/aideF2.wav";
	String ACCUEIL_SON = "../ressources/sons/accueil.wav";

	String FONT_TYPE_DEFAULT="Arial";
	int TAILLE_DEFAULT = 50;

	int NBR_SYNTHESE_NIVEAU = 3;
	
	Font FONT_DEFAULT[] = {new Font("Arial", Font.BOLD, 50),new Font("Arial", Font.BOLD, 60),new Font("Arial", Font.BOLD, 70),new Font("Arial", Font.BOLD, 90),new Font("Arial", Font.BOLD, 40)};
	
	java.awt.Color BACKGROUND_DEFAULT[] = {new Color(155,215,202),new Color(255,0,203),new Color(0,255,0)};
	java.awt.Color FOREGROUND_DEFAULT[] = {Color.BLACK,new Color(0,0,255),new Color(255,255,0)};
	java.awt.Color BUTTON_BACKGROUND_SELECTED_DEFAULT[] = {Color.BLACK,new Color(0,0,255),new Color(255,0,0)};
	java.awt.Color BUTTON_BACKGROUND_UNSELECTED_DEFAULT[] = {Color.WHITE,Color.WHITE,new Color(0,255,255)};
	java.awt.Color BUTTON_FOREGROUND_SELECTED_DEFAULT[] = {Color.WHITE,Color.WHITE,Color.WHITE};
	java.awt.Color BUTTON_FOREGROUND_UNSELECTED_DEFAULT[] = {Color.BLACK,Color.BLACK,Color.BLACK};
}
