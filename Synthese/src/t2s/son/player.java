package t2s.son;

import java.applet.*;
import java.net.MalformedURLException;
import java.net.URL;

import t2s.Constants;

/**
 * Fonction permettant d'adresser un son a un lecteur (un thread)
 * @author Justal "Latsuj" Kevin
 * @email justal.kevin@gmail.com
 */
public class Player extends Thread implements Constants {

	private AudioClip ac;
	private String path;
	private boolean loop;
	private URL u;
	
	/**
	 * Permet de creer un objet avec un path menant au fichier wav que l'on souhaite lire et son mode de lecture (repetitive ou no)
	 * @param path Le chemin vers le ficheir que l'on souhaite lire
	 * @param loop Le mode de lecture. Si true, lecture repetitive. Si false, lecture unique.
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public Player(String path,boolean loop) {
		logger.info("Player.class : Creation d'un player en mode loop="+loop+" pour "+path);
		this.path = path;
		this.loop = loop;
		
		try {
			u = new URL("file:"  + path);
			ac = Applet.newAudioClip(u);
		} catch (MalformedURLException e) {
			logger.warning("Player.class : Creation d'un player impossible (path incorrect)");
		}
	}
	
	/**
	 * Permet de retourner le chemin du fichier de ce lecteur
	 * @return Le path du fichier wav
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public String getPath() {
		return path;
	}
	
	@Override
	public void run() {
		logger.info("Player.class : Lecture du fichier "+this.path);
		if(loop) {
			ac.loop();
		} else {
    		ac.play();
		}
	}
	
	/**
	 * Permet de definir le mode de lecture du fichier
	 * @param loop Si true, mode de lecture repetitive. Si false, mode de lecture unique.
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void setLoop(boolean loop) {
		logger.info("Player.class : Changement de lecture du fichier "+this.path);
		this.loop = loop;
	}
	
	/**
	 * Permet de fermer le thread en cours d'utilisation de maniere propre
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void stopSong() {
		logger.info("Player.class : Fermeture du fichier "+this.path);
		ac.stop();
	}
}
