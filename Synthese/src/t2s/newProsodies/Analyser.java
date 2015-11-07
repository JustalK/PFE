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
package t2s.newProsodies;

import java.io.FileWriter;
import java.util.Vector;

import t2s.Constants;
import t2s.exception.AnalyseException;
import t2s.exception.PlusDePhraseException;
import t2s.prosodie.Phoneme;
import t2s.traitement.Arbre;
import t2s.traitement.Phrase;
import t2s.traitement.Pretraitement;
import t2s.util.ConfigFile;

public class Analyser implements Constants {
	
	private final String texte;
	private final int prosodie;
	
	private static String	CHEMIN_REGLES	= ConfigFile.rechercher("CHEMIN_REGLES");
	
	public Analyser(final String t, final int p) {
		this.texte = t;
		this.prosodie = p;
	}
	
	public Vector<Phoneme> analyserGroupes(String filename) {
		final Vector<Phrase> phrases = new Vector<Phrase>();
		String chainePhonemes;
		final Pretraitement pt = new Pretraitement(this.texte);
		
		try {
			final Arbre a = new Arbre(CHEMIN_REGLES);
			try {
				Phrase p = pt.nouvellePhrase();
				while (!"".equals(p.getPhrase())) {
					final String s[] = a.trouverPhoneme(p.getPhrase()).trim().replaceAll("  ", " ").split("%");
					for (int i = 0; i < s.length; ++i) {
						if (!"".equals(s[i])) {
							if (i == (s.length - 1)) {
								phrases.add(new Phrase(s[i].trim(), p.getProsodie()));
							} else {
								phrases.add(new Phrase(s[i].trim(), 3));
							}
						}
					}
					chainePhonemes = afficher(prosodier(phrases));
					try {
						final FileWriter fw = new FileWriter(ConfigFile.rechercher("REPERTOIRE_PHO_WAV") + ConfigFile.rechercher("FICHIER_PHO_WAV") + filename + ".pho");
						fw.write(chainePhonemes);
						fw.close();
					} catch (final Exception e) {
						logger.warning("La creation du fichier "+filename+" a echoue ou a recontrer un probleme !");
					}
					p = pt.nouvellePhrase();
				}
				
			} catch (final PlusDePhraseException e) {
			}
		} catch (final AnalyseException aa) {
			System.err.println(aa);
		}
		return prosodier(phrases);
	}
	
	public Vector<Phoneme> prosodier(Vector<Phrase> phrases) {
		if (this.prosodie == 1) {
			return new Prosodie1(phrases).prosodier();
		} else if (this.prosodie == 2) {
			return new Prosodie2(phrases).prosodier();
		} else if(this.prosodie == 3) {
			return new Prosodie3(phrases).prosodier();
		} else {
			logger.warning("La prosodie a une valeur incorrecte ("+this.prosodie+")");
			return null;
		}
	}
	
	public String getTexte() {
		return this.texte;
	}
	
	public String afficher(final Vector<Phoneme> l) {
		String s = new String();
		for (int i = 0; i < l.size(); ++i) {
			s += l.get(i).toString() + "\n";
		}
		return s;
	}
	
}
