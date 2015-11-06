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
	
	/**
	 * Permet de lire un texte par la synthese vocale de maniere repetitive
	 * @param text le texte que l'on souhaite faire lire par la synthese vocale
	 * @return Le nom du fichier
	 */
	public String loopText(final String text) {			
		String filename = createSynthetiseur(text);
		play("", LOOP_TEXTE);
		return filename;
	}

	/**
	 * Permet de faire lire un texte par la synthese vocale une seul fois
	 * @param text le texte que l'on souhaite faire lire par la synthese vocale
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void playText(final String text) {	
		createSynthetiseur(text);
		this.play("", PLAY_TEXTE);
	}	

	/**
	 * This method is deprecated ! 
	 * @param text Le texte que l'on souhaite faire lire par la synthese vocale
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	@Deprecated
	public void playShortText(final String text) {
		playText(text);
	}
	
	/**
	 * Permet de creer un synthetiseur avec un certain texte
	 * @param text Le texte qui sera utilise pour creer le fichier de phoneme
	 * @return Le nom du fichier qui sera cree
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private String createSynthetiseur(String text) {
		an = new Analyser(text, this.prosodie);
		String filename = ConfigFile.rechercher("REPERTOIRE_PHO_WAV") + ConfigFile.rechercher("FICHIER_PHO_WAV") + an.getTexte().hashCode();
		@SuppressWarnings("unused")
		final Vector<Phoneme> listePhonemes = an.analyserGroupes(filename+ ".pho");
		s = new SynthetiseurMbrola(jk, lt.getVoix(), ConfigFile.rechercher("REPERTOIRE_PHO_WAV"), ConfigFile.rechercher("FICHIER_PHO_WAV") + text.hashCode());
		return ConfigFile.rechercher("FICHIER_PHO_WAV") + an.getTexte().hashCode();
	}
	
	/**
	 * Permet de lire le fichier wav en boucle
	 * @param String Le chemin vers le fichier que l'on souhaite lire
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void loopWav(final String path) {
		this.play(path, LOOP_WAV);
	}
	
	/**
	 * Permet de lire un fichier wav une fois
	 * @param path Le chemin vers le fichier que l'on souhaite lire
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public void playWav(final String path) {
		this.play(path, PLAY_WAV);
	}
	
	/**
	 * This method is deprecated ! 
	 * @param path Le chemin vers le fichier
	 * @param loop Definie si le fichier doit etre lu de maniere repetitive
	 * @see playWav(final String path)
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	@Deprecated
	public void playWav(final String path,final boolean loop) {
		if(loop) {
			this.play(path, LOOP_WAV);
		} else {
			this.play(path, PLAY_WAV);
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
	
	/**
	 * Permet d'activer ou de desactiver la voix de synthese
	 */
	public void toggle() {
		this.on = !this.on;
	}
	
	/**
	 * Permet de retourner l'etat de la voix de synthese
	 * @return Retourne true si la voix de synthese est activee, false sinon
	 */
	public boolean getToggle() {
		return this.on;
	}
	
	/**
	 * Permet de creer des fichiers phoneme qui ne seront pas lu par la synthese vocale
	 * @param text Le texte que l'on souhaite lire
	 * @param out Le nom du fichier
	 */
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
			System.err.println("SIVOXDevint#muet : Erreur creation fichier phoneme.");
		}
		s = new SynthetiseurMbrola(jk, lt.getVoix(), out, "");
		s.muet();
	}
	
	/**
	 * Permet de gerer les differentes lectures en fonction des variables
	 * @param path Le path du fichier wav a lire, si il y a un fichier a lire 
	 * @param type Le type de la lecture
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private void play(final String path, int type) {
		if (!this.on) {
			return;
		}
		
		switch(type) {
			case LOOP_TEXTE: s.loop();
				break;
			case LOOP_WAV: this.jk.playBackgroundMusic(path);
				break;
			case PLAY_TEXTE:s.play(true);
				break;
			case PLAY_WAV: this.jk.playSound(path);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Permet de nettoyer l'application en supprimant les fichiers temporaires utilisés.
	 * @throws IOException
	 * @author Justal "Latsuj" Kevin
	 */
	public void clean() throws IOException {
		this.jk.killThread();
	}
}
