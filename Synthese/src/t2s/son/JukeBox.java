/*
 * source : https://github.com/hamilton-lima/jaga/blob/master/jaga%20desktop/src-desktop/com/athanazio/jaga/desktop/sound/Sound.java
 */

package t2s.son;

import java.io.File;

public class JukeBox {
	
	private Player player;
	
	public void stop(boolean stop) {
	}

	// False, wait , true
	public void playSound(String path, boolean loop, final boolean waitUntilAudioEnds) {
		//Player p = new Player(filename, loop, waitUntilAudioEnds, eraseAudioFileAfterPlayback);
		//playSound(p);
		File song = new File(path); 
		if(song.exists()) {
			player = new Player(path);
			player.start();
		} else {
			System.err.println("Le fichier suivant est introuvable :\n"+path);
		}
	}	
	
	// False, wait , true
	public void playSound(String filename, boolean loop, final boolean waitUntilAudioEnds, boolean eraseAudioFileAfterPlayback) {
		//Player p = new Player(filename, loop, waitUntilAudioEnds, eraseAudioFileAfterPlayback);
		//playSound(p);
	}
}
