package t2s.son;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Player extends Thread {

	private AudioClip ac;
	
	public Player(String path) {
		try {
			URL u = new URL("file:"  + path);
			ac = Applet.newAudioClip(u);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("azezaeazezae");
		ac.play();
	}
}
