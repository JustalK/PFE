/*
 * source : https://github.com/hamilton-lima/jaga/blob/master/jaga%20desktop/src-desktop/com/athanazio/jaga/desktop/sound/Sound.java
 */

package t2s.son;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import t2s.Constants;

/**
 * Permet de creer le jukebox qui gera les sons 
 * @author Justal "Latsuj" Kevin
 * @email justal.kevin@gmail.com
 */
public class JukeBox implements Constants {
	
	private Player player;
	private List<Player> listPlayerBackgroundMusics;
	private List<Player> listPlayerWaiting;
	private String previousSong;
	
	/**
	 * Permet de creer un JukeBox
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public JukeBox() {
		logger.info("JukeBox.Class : Creation d'un JukeBox");
		player = null;
		listPlayerBackgroundMusics = new ArrayList<Player>();
		listPlayerWaiting = new ArrayList<Player>();
	}
	
	/**
	 * Permet de lancer la lecture d'un son de maniere repetitive
	 * @param path Le pchemin du fichier que l'on souhaite lire de maniere continue
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void playBackgroundMusic(String path) 
	{
		logger.info("JukeBox.Class : Lancement d'une lecture en boucle de "+path);
		File song = new File(path);
		if(song.exists()) {
			Player tmp = new Player(path,true);
			listPlayerBackgroundMusics.add(tmp);
			tmp.start();
		} else {
			logger.warning("JukeBox.Class : Le fichier n'existe pas "+path);
		}
	}		
	
	/**
	 * Permet de lancer la lecture d'un son de maniere unique
	 * @param path Le chemin du fichier que l'on souhaite lire
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void playSound(String path) 
	{
		logger.info("JukeBox.Class : Lancement d'une lecture unique de "+path);
		File song = new File(path); 
		if(song.exists()) {
			if(player!=null && path!=null && !path.equals(player.getPath())) {
				closeCurrentThreadPlayer();
			}
			previousSong = song.getAbsolutePath().replaceFirst("[.][^.]+$", "");
			player = new Player(path,false);
			player.start();
		} else {
			logger.warning("JukeBox.Class : Le fichier n'existe pas "+path);	
		}
	}	
	
	/**
	 * Permet d'arreter tous les fichiers lues
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void stop() 
	{
		logger.info("Arret de la lecture de tous les sons");
		if(player!=null) {
			player.stopSong();
		}
		stopList(listPlayerWaiting);	
		stopList(listPlayerBackgroundMusics);	
	}

	/**
	 * Le chemin du fichier que l'on souhaite fermer
	 * @param path Le chemin du fichier que l'on souhaite stopper
	 * @return Retourne true si le fichier a ete stoppe, false sinon
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public boolean stop(String path) {
		if(path!=null) {
			if(player!=null && path.equals(player.getPath())) {
				logger.info("Arret de la lecture du fichier : "+path);
				player.stopSong();
				return true;
			}
			for(int i=0;i<listPlayerWaiting.size();i++) {
				if(path.equals(listPlayerWaiting.get(i).getPath())) {
					logger.info("Arret de la lecture du fichier : "+path);
					listPlayerWaiting.get(i).stopSong();
					return true;
				}
			}
			for(int i=0;i<listPlayerBackgroundMusics.size();i++) {
				if(path.equals(listPlayerBackgroundMusics.get(i).getPath())) {
					logger.info("Arret de la lecture du fichier : "+path);
					listPlayerBackgroundMusics.get(i).stopSong();
					return true;
				}
			}
		}
		logger.warning("Le fichier n'existe pas : "+path);
		return false;
	}
	
	/**
	 * Permet d'arreter la lecture de tous les fichiers qui ont ete joue avec Loop
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void stopBackgroundMusic() 
	{
		logger.info("Arret de la lecture de tous les sons qui boucle");
		stopList(listPlayerBackgroundMusics);
	}	
	
	/**
	 * Permet d'arreter tous les son jouer sauf ceux en jouer en boucle
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void stopNonBackgroundMusic() {
		logger.info("Arret de la lecture de tous les sons sauf ceux jouer en boucle");
		if(player!=null) {
			player.stopSong();
		}
		stopList(listPlayerWaiting);	
	}
	
	/**
	 * Permet de stopper la lecture d'une liste
	 * @param list La liste dont on souhaite stopper la lecture
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private void stopList(List<Player> list) {
		for(int i=0;i<list.size();i++) {
			list.get(i).stopSong();
		}		
	}	
	
	/**
	 * Permet de jouer un fichier creer de maniere silencieuse
	 * @param path Le chemin ver le fichier que l'on souhaite lire
	 * @param loop Si true on lira le fichier en boucle
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void playMuet(String path,boolean loop) {
		logger.info("JukeBox.Class : Lecture d'un fichier en mode loop="+loop+" pour "+path);
		File song = new File(path);
		if(song.exists()) {
			for(int i=0;i<listPlayerWaiting.size();i++) {
				if(path!=null && path.equals(listPlayerWaiting.get(i).getPath())) {
					listPlayerWaiting.get(i).setLoop(loop);
					listPlayerWaiting.get(i).start();
				}
			}
		} else {
			logger.warning("JukeBox.Class : Le fichier n'existe pas "+path);	
		}
	}	
	
	/**
	 * Permet de sauvegarder les fichiers creer de maniere silencieuse
	 * @param path Le chemin vers les fichiers creer de maniere silencieuse
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void saveMuet(String path) {
		logger.info("JukeBox.Class : Fermeture du fichier "+path);
		File song = new File(path);
		if(song.exists()) {
			Player tmp = new Player(path,false);
			listPlayerWaiting.add(tmp);
		} else {
			logger.warning("JukeBox.Class : Fichier qui n'existe pas ("+path+")");
		}
	}
	
	/**
	 * Permet de savoir si le fichier lu est un fichier "ressources"
	 * @param path Path du fichier 
	 * @return Retourne true si le fichier est une ressource ou false sinon
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private boolean isRessources(String path) {
		if(path!=null && path.contains("ressources")) {
			return true;
		}
		return false;
	}	
	
	/**
	 * Permet de finir tous les threads courant petu importe ce qu'ils font.
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void killThread() {
		logger.info("JukeBox.Class : Fermer l'ensemble des threads");
		closeCurrentThreadPlayer();
		killList(listPlayerBackgroundMusics);
		killList(listPlayerWaiting);
	}

	/**
	 * Permet de supprimer proprement le dernier fichier wav et pho utilisé
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void closeCurrentThreadPlayer() {
		if(player!=null) {
			logger.info("JukeBox.Class : Fermeture du fichier "+player.getPath());
			player.stopSong();
			player.interrupt();
			while(player.isAlive()) {
				// On attend que le thread soit ferme pour avancer
			}
			if(!isRessources(player.getPath())) {
				File pho = new File(previousSong+".pho");
				File wav = new File(previousSong+".wav");
				pho.delete();
				wav.delete();
			} else {
				logger.info("JukeBox.Class : Non suppression du fichier "+player.getPath());
			}
		}
	}	
	
	/**
	 * Permet de fermer tous les threads proprement contenu dans une liste
	 * @param list La liste dont on souhaite supprimer les threads
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private void killList(List<Player> list) {
		for(int i=0;i<list.size();i++) {
			list.get(i).stopSong();
			File wav = new File(list.get(i).getPath().replaceFirst("[.][^.]+$", "")+".wav");
			File pho = new File(list.get(i).getPath().replaceFirst("[.][^.]+$", "")+".pho");
			if(!isRessources(list.get(i).getPath())) {
				wav.delete();
				pho.delete();		
			}
		}		
	}
}
