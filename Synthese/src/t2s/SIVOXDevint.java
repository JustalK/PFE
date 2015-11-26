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

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import t2s.newProsodies.Analyser;
import t2s.son.JukeBox;
import t2s.son.LecteurTexte;
import t2s.son.SimpleTextToSpeech;
import t2s.son.SynthetiseurMbrola;
import t2s.util.ConfigFile;

/**
 * Une A.P.I. pour utiliser la synthèse vocale dans les projets DeViNT
 * 
 * @author Ecole Polytechnique Universitaire Nice Sophia Antipolis
 */
public final class SIVOXDevint implements Constants {
	private JukeBox	           jk;	     // pour jouer les wav
	private LecteurTexte	   lt;	     // pour choisir une voix
	private SynthetiseurMbrola	s;
	private Analyser	       an;
	private boolean	           on;	     // true/false pour valider/invalider la synthèse SIVOX
	private int	               prosodie; // code la prosodie utilisée, de 1 à 3 (3 par défaut)
	
	/**
	 * Constructeur par défaut : voix de Thierry
	 * prosodie = 3, la plus performante
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public SIVOXDevint() {
		this(VOIX_DEFAUT,true,PROSODIE_DEFAUT);
	}

	/**
	 * Constructeur necessaire pour la retrocompatibilite
	 * @param voix La voix utilise si la synthese parle
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public SIVOXDevint(final int voix) {	
		this(voix,true,PROSODIE_DEFAUT);
	}	
	
	/**
	 * Permet de creer une voix de synthese avec l'ensemble des configuration possible
	 * @param voix La voix utilise si la synthese parle
	 * @param on Permet de savoir si la voix est active ou non
	 * @param prosodie La prosodie de la voix de synthese
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public SIVOXDevint(final int voix,final boolean on,final int prosodie) {
		try {
			FileHandler fh = new FileHandler(System.getProperty("java.io.tmpdir")+"Latsuj.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		this.jk = new JukeBox();
		this.lt = new LecteurTexte(jk);
		this.on = on;
		lt.setVoix(1);
		this.prosodie = prosodie;
		this.setVoix(voix);
		logger.info("Creation d'une SIVOXDevint [ prosodie : "+this.prosodie+" | Voix : "+voix+" | Etat : "+this.on+" ]");
	}
	
	/**
	 * Permet de lire un texte par la synthese vocale de maniere repetitive
	 * @param text le texte que l'on souhaite faire lire par la synthese vocale
	 * @return Le nom du fichier
	 */
	public final String loopText(final String text) {	
		logger.info("Lecture LOOP du texte : "+text);		
		String filename = createSynthetiseur(text,"");
		action("", LOOP_TEXTE);
		return filename;
	}

	/**
	 * Permet de faire lire un texte par la synthese vocale une seul fois
	 * @param text le texte que l'on souhaite faire lire par la synthese vocale
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void playText(final String text) {
		logger.info("Lecture UNIQUE du texte : "+text);	
		createSynthetiseur(text,"");
		this.action("", PLAY_TEXTE);
	}	

	/**
	 * This method is deprecated ! 
	 * @param text Le texte que l'on souhaite faire lire par la synthese vocale
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	@Deprecated
	public final void playShortText(final String text) {
		playText(text);
	}
	
	/**
	 * Permet de creer un synthetiseur avec un certain texte
	 * @param text Le texte qui sera utilise pour creer le fichier de phoneme
	 * @param filename Le nom du fichier qui sera creer. Si le filename est la string vide, on cree un fichier avec le nom inscrit dans la variable
	 * @return Le nom du fichier qui sera cree
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private String createSynthetiseur(String text,String filename) {
		an = new Analyser(text, this.prosodie);
		if(filename=="" || filename==null) {
			an.analyserGroupes(""+text.hashCode());
			s = new SynthetiseurMbrola(jk, lt.getVoix(), ConfigFile.rechercher("REPERTOIRE_PHO_WAV"), ConfigFile.rechercher("FICHIER_PHO_WAV") + text.hashCode());
		} else {
			an.analyserGroupes(filename);
			s = new SynthetiseurMbrola(jk, lt.getVoix(), ConfigFile.rechercher("REPERTOIRE_PHO_WAV"), ConfigFile.rechercher("FICHIER_PHO_WAV") + filename);			
		}
		return ConfigFile.rechercher("FICHIER_PHO_WAV") + an.getTexte().hashCode();
	}
	
	/**
	 * Permet de lire le fichier wav en boucle
	 * @param String Le chemin vers le fichier que l'on souhaite lire
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void loopWav(final String path) {
		logger.info("Lecture LOOP du wav : "+path);
		this.action(path, LOOP_WAV);
	}
	
	/**
	 * Permet de lire un fichier wav une fois
	 * @param path Le chemin vers le fichier que l'on souhaite lire
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void playWav(final String path) {
		logger.info("Lecture UNIQUE du wav : "+path);
		this.action(path, PLAY_WAV);
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
	public final void playWav(final String path,final boolean loop) {
		if(loop) {
			this.action(path, LOOP_WAV);
		} else {
			this.action(path, PLAY_WAV);
		}
	}
	
	/**
	 * Permet de fixer la prosodie de la synthese vocale
	 * La valeur est situe entre 1 et 3. Si la valeur est inferieur a 1, la prosodie sera automatiquement fixe a 1.
	 * Si la valeur de la prosodie est au contraire superieur a 3, la prosodie sera automatiquement fixe a 3.
	 * @param p un entier
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void setProsodie(final int p) {
		logger.info("Modification de la prosodie : "+p);
		
		if(p<1) {
			this.prosodie = 1;
		} else if(p>3) {
			this.prosodie = 3;
		} else {
			this.prosodie = p;
		}
	}
	
	/**
	 * Permet de retourner la valeur de la prosodie de la synthese vocale
	 * @return Un entier entre 1 et 3
	 */
	public final int getProsodie() {
		return this.prosodie;
	}
	
	/**
	 * Permet de fixer la voix utilise si la synthese parle
	 * @param voix Un entier entre 1 et 7
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void setVoix(final int voix) {
		logger.info("Modification de la voix : "+voix);
		
		final int nbvoix = Integer.parseInt(ConfigFile.rechercher("NBVOIX"));
		int vox;
		if(voix > nbvoix) {
			vox = nbvoix;
		} else if(voix < 1) {
			vox = 1;
		} else {
			vox = voix;
		}
		lt.setVoix(vox);
	}
	
	public final void googlePlay(String text) throws Exception {
        String textEncode = URLEncoder.encode(text, "utf-8");
        new SimpleTextToSpeech().go(textEncode);
	}
	
	/**
	 * Pour stopper la synthèse vocale et donc arréter le son en cours de lecture
	 * on stoppe le jukebox jk, qui lit les sons wave, le lecteur texte lt, et la synthèse s
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void stop() {
		if (this.jk != null) {
			this.jk.stop();
		} else {
			logger.info("La jukebox n'a pas ete inititalise");
		}
	}
	
	/**
	 * Permet de stopper un fichier son en particulier
	 * @param path Le chemin du son dont l'on souhaite arreter la lecture
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void stop(String path) {
		if (this.jk != null) {
			this.jk.stop(path);
		} else {
			logger.info("La jukebox n'a pas ete inititalise");
		}
	}
	
	/**
	 * Permet de stopper l'ensemble des sons qui sont jouer de maniere repetitive
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void stopBackgroundMusics() {
		if (this.jk != null) {
			this.jk.stopBackgroundMusic();;
		} else {
			logger.info("La jukebox n'a pas ete inititalise");
		}
	}

	/**
	 * Permet de stopper l'ensemble des sons qui sont jouer sauf ceux qui sont jouer de maniere repetitive
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void stopNonBackgroundMusics() {
		if (this.jk != null) {
			this.jk.stopNonBackgroundMusic();
		} else {
			logger.info("La jukebox n'a pas ete inititalise");
		}
	}	
	
	/**
	 * Permet d'activer ou de desactiver la voix de synthese
	 */
	public final void toggle() {
		logger.info("Desactivation/activation de la voix de synthese : "+on);
		this.on = !this.on;
	}
	
	/**
	 * Permet de retourner l'etat de la voix de synthese
	 * @return Retourne true si la voix de synthese est activee, false sinon
	 */
	public final boolean getToggle() {
		return this.on;
	}
	
	/**
	 * Permet de creer des fichiers phoneme qui ne seront pas lu par la synthese vocale
	 * @param text Le texte que l'on souhaite lire
	 * @param filename Le nom du fichier qui sera cree
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void muet(final String text, final String filename) {
		logger.info("Creation silencieuse du fichier wav : "+filename);
		createSynthetiseur(text,filename);
		action("", MUET);
	}
	
	/**
	 * Permet de lire un fichier wav a partir de son nom
	 * @param filename Le nom du fichier que l'on souhaite lire (exemple : latsuj.wav)
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void playWavWithFilename(final String filename) {
		logger.info("SIVOXDevint.class : Lecture unique d'un fichier wav cree silencieusement "+filename);
		action(System.getProperty("java.io.tmpdir")+ConfigFile.rechercher("FICHIER_PHO_WAV") + filename+".wav", PLAY_MUET);
	}

	/**
	 * Permet de lire un fichier wav a partir de son nom
	 * @param filename Le nom du fichier que l'on souhaite lire (nom de fichier sans l'extension)
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	public final void loopWavWithFilename(final String filename) {
		logger.info("SIVOXDevint.class : Lecture en boucle d'un fichier wav cree silencieusement "+filename);
		action(System.getProperty("java.io.tmpdir")+ConfigFile.rechercher("FICHIER_PHO_WAV") +filename+".wav", LOOP_MUET);
	}	
	
	/**
	 * Permet de gerer les differentes lectures en fonction des variables
	 * @param path Le path du fichier wav a lire, si il y a un fichier a lire 
	 * @param type Le type de la lecture
	 * @author Justal "Latsuj" Kevin
	 * @email justal.kevin@gmail.com
	 */
	private void action(final String path, int type) {
		if (this.on) {
			switch(type) {
				case LOOP_TEXTE: s.loop();
					break;
				case LOOP_WAV: this.jk.playBackgroundMusic(path);
					break;
				case PLAY_TEXTE:s.play(true);
					break;
				case PLAY_WAV: 
					this.jk.playSound(path);
					break;
				case MUET: s.muet();
					break;
				case PLAY_MUET: this.jk.playMuet(path,false);
					break;
				case LOOP_MUET: this.jk.playMuet(path,true);
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * Permet de nettoyer l'application en supprimant les fichiers temporaires utilisés.
	 * @throws IOException
	 * @author Justal "Latsuj" Kevin
	 */
	public final void clean() {
		logger.info("Suppression des fichiers temporaires");
		this.jk.killThread();
	}
}
