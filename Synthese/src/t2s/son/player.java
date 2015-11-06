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
	private boolean loop;
	private URL u;
	
	public Player(String path,boolean loop) {
		this.path = path;
		this.loop = loop;
		try {
			u = new URL("file:"  + path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ac = Applet.newAudioClip(u);
	}
	
	public String getPath() {
		return path;
	}
	
	@Override
	public void run() {
		if(loop) {
			ac.loop();
		} else {
    		ac.play();
		}
	}
	
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	/**
	 * Permet de fermer le thread en cours d'utilisation de maniere propre
	 */
	public void stopSong() {
		ac.stop();
	}
}
