/*
 * source : https://github.com/hamilton-lima/jaga/blob/master/jaga%20desktop/src-desktop/com/athanazio/jaga/desktop/sound/Sound.java
 */

package t2s.son;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class JukeBox {
	
	
	private ReentrantLock	mutex;
	private String _fileToRead;
	
	public void stop(boolean stop) {
		
	}

	// False, wait , true
	public void playSound(String filename, boolean loop, final boolean waitUntilAudioEnds) {
		//Player p = new Player(filename, loop, waitUntilAudioEnds, eraseAudioFileAfterPlayback);
		//playSound(p);
	}	
	
	// False, wait , true
	public void playSound(String filename, boolean loop, final boolean waitUntilAudioEnds, boolean eraseAudioFileAfterPlayback) {
		//Player p = new Player(filename, loop, waitUntilAudioEnds, eraseAudioFileAfterPlayback);
		//playSound(p);
	}
}
