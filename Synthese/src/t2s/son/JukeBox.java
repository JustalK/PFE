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

	public void playSound(String path) {
		File song = new File(path); 
		System.out.println(path);
		// Is the file exist ?
		if(song.exists()) {
			// If it's the first start, we have not any player
			if(player!=null) {
				player.stopSong();
				player.interrupt();
				if(player.isAlive()) {
					System.err.println("Le thread is not over !");	
				} else {
					System.out.println(previousSong);
					File pho = new File(previousSong+".pho");
					File wav = new File(previousSong+".wav");
					pho.delete();
					wav.delete();
				}
			}
			previousSong = song.getAbsolutePath().replaceFirst("[.][^.]+$", "");
			player = new Player(path);
			player.start();
		} else {
			System.err.println("Le fichier suivant est introuvable :\n"+path);
		}
	}	
	
	public void playBackgroundMusic(String filename) {
		
	}	
	
}
