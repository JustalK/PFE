/*
 * ﻿Copyright 2004-2007, Christian BREL, Hélène COLLAVIZZA, Sébastien MOSSER, Jean-Paul STROMBONI,
 * This file is part of project 'VocalyzeSIVOX'
 * 'VocalyzeSIVOX' is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 'VocalyzeSIVOX'is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with 'VocalyzeSIVOX'. If not, see <http://www.gnu.org/licenses/>.
 */

package t2s;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import t2s.newProsodies.Analyser;
import t2s.prosodie.Phoneme;
import t2s.son.JukeBox;
import t2s.son.LecteurTexte;
import t2s.son.SynthetiseurMbrola;
import t2s.util.ConfigFile;

/**
 * Une A.P.I. pour utiliser la synthèse vocale dans les projets DeViNT
 * 
 * @author Ecole Polytechnique Universitaire Nice Sophia Antipolis
 */
public class SIVOXDevint implements Constants {
	private JukeBox	           jk;	     // pour jouer les wav
	private LecteurTexte	   lt;	     // pour choisir une voix
	private SynthetiseurMbrola	s;
	private Analyser	       an;
	private boolean	           on;	     // true/false pour valider/invalider la synthèse SIVOX
	private int	               prosodie; // code la prosodie utilisée, de 1 à 3 (3 par défaut)
	/**
	 * Constructeur par défaut : voix de Thierry
	 * prosodie = 3, la plus performante
	 */
	public SIVOXDevint() {
		this.jk = new JukeBox();
		this.lt = new LecteurTexte(jk);
		this.on = true;
		lt.setVoix(1);
		this.prosodie = 3;
	}
	
	/**
	 * Constructeur pour fixer la voix
	 * 
	 * @param voix
	 *            , de 1 à 7 pour fr1, fr2, ... fr7
	 */
	public SIVOXDevint(final int voix) {
		this();
		final int nbvoix = Integer.parseInt(ConfigFile.rechercher("NBVOIX")); // nombre de voix disponibles
		int vox;
		vox = (voix > nbvoix) ? nbvoix : voix;
		vox = (voix < 1) ? 1 : voix;
		lt.setVoix(vox);
	}
	
	
	public String loopText(final String text) {			
		String filename = createSynthetiseur(text);
		play(text, LOOP_TEXTE);
		return filename;
	}

	public void playText(final String text) {	
		createSynthetiseur(text);
		this.play(text, PLAY_TEXTE);
	}	

	@Deprecated
	public void playShortText(final String text) {
		createSynthetiseur(text);
		this.play(text, PLAY_TEXTE);
	}
	
	private String createSynthetiseur(String text) {
		an = new Analyser(text, this.prosodie);
		String filename = ConfigFile.rechercher("REPERTOIRE_PHO_WAV") + ConfigFile.rechercher("FICHIER_PHO_WAV") + an.getTexte().hashCode();
		@SuppressWarnings("unused")
		final Vector<Phoneme> listePhonemes = an.analyserGroupes(filename+ ".pho");
		s = new SynthetiseurMbrola(jk, lt.getVoix(), ConfigFile.rechercher("REPERTOIRE_PHO_WAV"), ConfigFile.rechercher("FICHIER_PHO_WAV") + text.hashCode());
		return ConfigFile.rechercher("FICHIER_PHO_WAV") + an.getTexte().hashCode();
	}
	/**
	 * Pour lire le son d'un fichier .wav en boucle
	 * 
	 * @param fichier
	 *            nom du fichier (wave) à lire
	 */
	public void loopWav(final String fichier) {
		this.play(fichier, LOOP_WAV);
	}
	
	/**
	 * Pour lire le son d'un fichier .wav
	 * 
	 * @param fichier
	 *            wave à lire
	 */
	public void playWav(final String fichier) {
		this.play(fichier, PLAY_WAV);
	}
	
	@Deprecated
	public void playWav(final String text,boolean loop) {
		if(loop) {
			this.play(text, LOOP_WAV);
		} else {
			this.play(text, PLAY_WAV);
		}
	}
	
	/**
	 * Pour fixer la prosodie utilisée
	 * 
	 * @param p
	 *            , entier de 1 à 3
	 */
	public void setProsodie(final int p) {
		int pro;
		pro = (p < 1) ? 1 : p;
		pro = (p > 3) ? 3 : p;
		this.prosodie = pro;
	}
	
	public int getProsodie() {
		return this.prosodie;
	}
	
	/**
	 * Pour fixer la voix utilisée si la synthèse parle
	 * 
	 * @param voix
	 *            , de 1 à 7
	 */
	public void setVoix(final int voix) {
		int vox;
		final int nbvoix = Integer.parseInt(ConfigFile.rechercher("NBVOIX")); // nombre de voix disponibles dans ressources
		vox = (voix > nbvoix) ? nbvoix : voix;
		vox = (voix < 1) ? 1 : voix;
		lt.setVoix(vox);
	}
	
	/**
	 * Pour stopper la synthèse vocale et donc arréter le son en cours de lecture
	 * on stoppe le jukebox jk, qui lit les sons wave, le lecteur texte lt, et la synthèse s
	 */
	public void stop() {
		if (this.jk != null) {
			this.jk.stop();
		}
	}
	
	// Pour basculer entre voix on / voix off
	public void toggle() {
		this.on = !this.on;
	}
	
	/**
	 * renvoie l'état du toggle voix on/voix off
	 * 
	 * @return
	 */
	public boolean getToggle() {
		return this.on;
	}
	
	// pour créer des fichiers wave en silence
	public void muet(final String text, final String out) {
		// if ( !on ) return;
		an = new Analyser(text, this.prosodie);
		String filename = ConfigFile.rechercher("REPERTOIRE_PHO_WAV") + ConfigFile.rechercher("FICHIER_PHO_WAV") + an.getTexte().hashCode();
		final Vector<Phoneme> listePhonemes = an.analyserGroupes(filename+".pho");
		final String chainePho = an.afficher(listePhonemes);
		try {
			final FileWriter fw = new FileWriter(out + ".pho");
			fw.write(chainePho);
			fw.close();
		} catch (final Exception e) {
			System.out.println("erreur création fichier phonème.");
		}
		s = new SynthetiseurMbrola(jk, lt.getVoix(), out, "");
		s.muet();
	}
	
	// appelé par loopText et playText avec valeur flagloop diff�rente
	private void play(final String text, int type) {
		if (!this.on) {
			return;
		}
		
		switch(type) {
			case LOOP_TEXTE: s.loop();
				break;
			case LOOP_WAV: this.jk.playBackgroundMusic(text);
				break;
			case PLAY_TEXTE:s.play(true);
				break;
			case PLAY_WAV: this.jk.playSound(text);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Permet de nettoyer l'application en supprimant les fichiers temporaires utilisés.
	 * @throws IOException
	 */
	public void clean() throws IOException {
		this.jk.killThread();
	}
}
