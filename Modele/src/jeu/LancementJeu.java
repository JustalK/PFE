package jeu;

import java.io.File;
import java.io.IOException;

import t2s.SIVOXDevint;

/** classe pour lancer le jeu
 * Elle crée simplement une instance de MenuJeu
 * 
 * @author helene
 *
 */
public class LancementJeu{

    public static void main(String args[]) throws IOException{
			new jeu.MenuJeu("Exemple de jeu");
			SIVOXDevint.clean();
    }
}
