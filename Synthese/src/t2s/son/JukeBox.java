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
	private String previousSong;
	
	public JukeBox() {
		player = null;
		listPlayerBackgroundMusics = new ArrayList<Player>();
	}
	
	public void stop(boolean stop) 
	{
		
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
	
	public void playBackgroundMusic(String path) 
	{
		File song = new File(path);
		if(song.exists()) {
			Player tmp = new Player(path,true);
			listPlayerBackgroundMusics.add(tmp);
			tmp.start();
		}
	}	
	
	/**
	 * Permet de supprimer proprement le dernier fichier wav et pho utilisé
	 */
	public void closeCurrentThreadPlayer() {
		player.stopSong();
		player.interrupt();
		if(player.isAlive()) {
			System.err.println("Le thread is not over !");	
		} else {
			File pho = new File(previousSong+".pho");
			File wav = new File(previousSong+".wav");
			pho.delete();
			wav.delete();
		}		
	}
	
	/**
	 * Permet de finir tous les threads courant petu importe ce qu'ils font.
	 */
	public void killThread() {
		if(player!=null) {
			closeCurrentThreadPlayer();
		}
		for(int i=0;i<listPlayerBackgroundMusics.size();i++) {
			listPlayerBackgroundMusics.get(i).stopSong();
			File wav = new File(listPlayerBackgroundMusics.get(i).getPath().replaceFirst("[.][^.]+$", "")+".wav");
			File pho = new File(listPlayerBackgroundMusics.get(i).getPath().replaceFirst("[.][^.]+$", "")+".pho");
			wav.delete();
			pho.delete();
		}
	}
	
}
