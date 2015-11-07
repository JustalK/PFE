/*
 * source : https://github.com/hamilton-lima/jaga/blob/master/jaga%20desktop/src-desktop/com/athanazio/jaga/desktop/sound/Sound.java
 */

package t2s.son;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JukeBox {
	
	private Player player;
	private List<Player> listPlayerBackgroundMusics;
	private List<Player> listPlayerWaiting;
	private String previousSong;
	
	public JukeBox() {
		player = null;
		listPlayerBackgroundMusics = new ArrayList<Player>();
		listPlayerWaiting = new ArrayList<Player>();
	}
	
	
	
	/**
	 * Permet de jouer un fichier creer de maniere silencieuse
	 * @param path Le chemin ver le fichier que l'on souhaite lire
	 * @param loop Si true on lira le fichier en boucle
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void playMuet(String path,boolean loop) {
		File song = new File(path);
		if(song.exists()) {
			for(int i=0;i<listPlayerWaiting.size();i++) {
				if(path!=null && path.equals(listPlayerWaiting.get(i).getPath())) {
					listPlayerWaiting.get(i).setLoop(loop);
					listPlayerWaiting.get(i).start();
				}
			}
		}
	}
	
	public void playBackgroundMusic(String path) 
	{
		File song = new File(path);
		if(song.exists()) {
			Player tmp = new Player(path,true);
			listPlayerBackgroundMusics.add(tmp);
			tmp.start();
		}
	}		
	
	public void playSound(String path) 
	{
		File song = new File(path); 
		// Is the file exist ?
		if(song.exists()) {
			// If it's the first start, we have not any player
			
			if(player!=null) {
				closeCurrentThreadPlayer();
			}
			previousSong = song.getAbsolutePath().replaceFirst("[.][^.]+$", "");
			player = new Player(path,false);
			player.start();
		} else {
			System.err.println("Le fichier suivant est introuvable :\n"+path);
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
	
	public void stop() 
	{
		
	}
	
	/**
	 * Permet de supprimer proprement le dernier fichier wav et pho utilisé
	 */
	public void closeCurrentThreadPlayer() {
		if(player!=null) {
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
			}	
		}
	}
	
	/**
	 * Permet de finir tous les threads courant petu importe ce qu'ils font.
	 *
	 */
	//TODO DUPLICATION ! I HAVE TO CHANGE THAT
	public void killThread() {
		if(player!=null) {
			closeCurrentThreadPlayer();
		}
		for(int i=0;i<listPlayerBackgroundMusics.size();i++) {
			listPlayerBackgroundMusics.get(i).stopSong();
			File wav = new File(listPlayerBackgroundMusics.get(i).getPath().replaceFirst("[.][^.]+$", "")+".wav");
			File pho = new File(listPlayerBackgroundMusics.get(i).getPath().replaceFirst("[.][^.]+$", "")+".pho");
			if(!isRessources(listPlayerBackgroundMusics.get(i).getPath())) {
				wav.delete();
				pho.delete();
			}
		}
		for(int i=0;i<listPlayerWaiting.size();i++) {
			listPlayerWaiting.get(i).stopSong();
			File wav = new File(listPlayerWaiting.get(i).getPath().replaceFirst("[.][^.]+$", "")+".wav");
			File pho = new File(listPlayerWaiting.get(i).getPath().replaceFirst("[.][^.]+$", "")+".pho");
			if(!isRessources(listPlayerWaiting.get(i).getPath())) {
				wav.delete();
				pho.delete();		
			}
		}
		
	}

	public void saveMuet(String path) {
		File song = new File(path);
		if(song.exists()) {
			Player tmp = new Player(path,false);
			listPlayerWaiting.add(tmp);
		}
	}
	
}
