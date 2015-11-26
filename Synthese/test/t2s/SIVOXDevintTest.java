package t2s;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SIVOXDevintTest {
	private SIVOXDevint sd;
	
    @Before
    public void setUp() throws Exception {
        sd = new SIVOXDevint();  
    }

    @After
    public void tearDown() throws Exception {
    	// Permet de supprimer proprement tous les fichiers dans temps
    	sd.clean();
    	// Les fichiers .wav ne doivent pas etre effacer
    	File file = new File("./../ressources/sons/accueil.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	} 
    	file = new File("./../ressources/sons/accueilFichier.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	} 
    }    
    
    @Test
    public void testSIVOXDevintDefault() {
        Assert.assertTrue(sd.getProsodie()==3);
    } 
    
    @Test
    public void testPlayText() {
    	sd.playText("aaa");
    	// Le fichier doit existe dans le dossier temps
    	File file = new File(System.getProperty("java.io.tmpdir")+"phrase96321.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}
    	sd.playText("bbb");
    	// Le fichier precedent ne doit plus exister dans le dossier temp car on a ouvert un autre fichier
    	if(file.exists()) {
    		Assert.fail();
    	}
    }  
    
    @Test
    public void testLoopText() {
    	sd.loopText("aaa");
    	// Le fichier doit existe dans le dossier temps
    	File file = new File(System.getProperty("java.io.tmpdir")+"phrase96321.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}
    	sd.loopText("bbb");
    	// Le fichier precedent DOIT toujours exister car c'est un loop
    	if(!file.exists()) {
    		Assert.fail();
    	}
    }
    
    @Test
    public void testPlayShortText() {
    	sd.playShortText("aaa");
    	// Le fichier doit existe dans le dossier temps
    	File file = new File(System.getProperty("java.io.tmpdir")+"phrase96321.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}
    	sd.playShortText("bbb");
    	// Le fichier precedent ne doit plus exister dans le dossier temp car on a ouvert un autre fichier
    	if(file.exists()) {
    		Assert.fail();
    	}    	
    }
    
    @Test
    public void testLoopWav() {
    	sd.loopWav("./../ressources/sons/accueil.wav");
    	sd.loopWav("./../ressources/sons/accueilFichier.wav");
    	// Les fichiers .wav ne doivent pas etre effacer
    	File file = new File("./../ressources/sons/accueil.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	} 
    	file = new File("./../ressources/sons/accueilFichier.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}  
    }

    @Test
    public void testPlayWav() {
    	sd.playWav("./../ressources/sons/accueil.wav");
    	sd.playWav("./../ressources/sons/accueilFichier.wav");
    	// Les fichiers .wav ne doivent pas etre effacer
    	File file = new File("./../ressources/sons/accueil.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	} 
    	file = new File("./../ressources/sons/accueilFichier.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}     	
    }
    
    @Test
    public void testPlayWavOld() {
    	sd.playWav("./../ressources/sons/accueil.wav",true);
    	sd.playWav("./../ressources/sons/accueilFichier.wav",false);
    	// Les fichiers .wav ne doivent pas etre effacer
    	File file = new File("./../ressources/sons/accueil.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	} 
    	file = new File("./../ressources/sons/accueilFichier.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}    	
    }
    
    @Test
    public void testSetGetProsodie() {
    	Assert.assertTrue(sd.getProsodie()==3);
    	sd.setProsodie(10);
    	Assert.assertTrue(sd.getProsodie()==3);
    	sd.setProsodie(4);
    	Assert.assertTrue(sd.getProsodie()==3);
    	sd.setProsodie(2);
    	Assert.assertTrue(sd.getProsodie()==2);
    	sd.setProsodie(3);
    	Assert.assertTrue(sd.getProsodie()==3);
    	sd.setProsodie(-1);
    	Assert.assertTrue(sd.getProsodie()==1);
    	sd.setProsodie(0);
    	Assert.assertTrue(sd.getProsodie()==1);
    	sd.setProsodie(1);
    	Assert.assertTrue(sd.getProsodie()==1);
    }
    
    @Test
    public void testToggleGetToggle() {
    	Assert.assertTrue(sd.getToggle());
    	sd.toggle();
    	Assert.assertFalse(sd.getToggle());
    	sd.toggle();
    	Assert.assertTrue(sd.getToggle());
    }
    
    @Test
    public void testMuet() {
    	sd.muet("aaa","Latsuj");
    	sd.muet("aaa","Justal");
    	// Les fichiers .wav cree via la method muet ne doivent pas etre effacer a chaque fois et doivent avoir un certain nom
    	File file = new File(System.getProperty("java.io.tmpdir")+"Latsuj.wav");
    	if(file.exists()) {
    		Assert.fail();
    	} 
    	file = new File(System.getProperty("java.io.tmpdir")+"Justal.wav");
    	if(file.exists()) {
    		Assert.fail();
    	} 
    }
}
