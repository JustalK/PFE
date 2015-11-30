package jeu;
import java.awt.event.KeyEvent;
import java.util.Set;

import devint.Constantes;

public class Moteur implements Constantes {
    private Set<Integer> keyPressed; 
	private boolean game;
    
    public Moteur(Set<Integer> keyPressed) {
    	this.keyPressed = keyPressed;
    	game = true;
    }
    
    public void loop() {
        while (game) {
        	update();
        }
    }
    
    public void update() {
    	if(keyPressed.contains(KeyEvent.VK_ESCAPE)) {
    		game = !game;
    	}
    }
    
    public void render() {
    	
    }
}
