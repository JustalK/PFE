package t2s.son;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends Thread {

	private AudioClip ac;
	private String path;
	public Player(String path) {
		this.path = path;
	}
	
	@Override
	public void run() {
		try {
    		URL u = new URL("file:"  + path);
    		ac = Applet.newAudioClip(u);
    		ac.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de fermer le thread en cours d'utilisation de maniere propre
	 */
	public void stopSong() {
		ac.stop();
	}
}
