package dvt.devint;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dvt.jeuchronometre.Chronometer;
import dvt.jeuchronometre.JeuChrono;

public class FenetreTest {
    private static JeuChrono f;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        f = new JeuChrono();
    }    
    
    @Test
    public void testSyntheseNiveau() {
        assertTrue(f.getSyntheseNiveau()==2);
        f.changeSyntheseNiveau();
        assertTrue(f.getSyntheseNiveau()==0);
        f.changeSyntheseNiveau();
        assertTrue(f.getSyntheseNiveau()==1);
    }
}
