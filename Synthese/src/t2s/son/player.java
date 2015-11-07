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

import t2s.Constants;

public class Player extends Thread implements Constants {

	private AudioClip ac;
	private String path;
	private boolean loop;
	private URL u;
	
	public Player(String path,boolean loop) {
		logger.info("Player.class : Creation d'un player en mode loop="+loop+" pour "+path);
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
		logger.info("Player.class : Lecture du fichier "+this.path);
		if(loop) {
			ac.loop();
		} else {
    		ac.play();
		}
	}
	
	public void setLoop(boolean loop) {
		logger.info("Player.class : Changement de lecture du fichier "+this.path);
		this.loop = loop;
	}
	
	/**
	 * Permet de fermer le thread en cours d'utilisation de maniere propre
	 */
	public void stopSong() {
		logger.info("Player.class : Fermeture du fichier "+this.path);
		ac.stop();
		Thread.interrupted();
	}
}
