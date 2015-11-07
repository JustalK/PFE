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
	 */
	public void playSound(String path) 
	{
		logger.info("JukeBox.Class : Lancement d'une lecture unique de "+path);
		File song = new File(path); 
		if(song.exists()) {
			if(player!=null) {
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
	 * Permet d'arreter la lecture de tous les fichiers
	 */
	public void stop() 
	{
		//TODO A faire
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
	 */
	private boolean isRessources(String path) {
		if(path!=null && path.contains("ressources")) {
			return true;
		}
		return false;
	}	
	
	/**
	 * Permet de finir tous les threads courant petu importe ce qu'ils font.
	 */
	public void killThread() {
		logger.info("JukeBox.Class : Fermer l'ensemble des threads");
		closeCurrentThreadPlayer();
		killList(listPlayerBackgroundMusics);
		killList(listPlayerWaiting);
	}

	/**
	 * Permet de supprimer proprement le dernier fichier wav et pho utilisé
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
