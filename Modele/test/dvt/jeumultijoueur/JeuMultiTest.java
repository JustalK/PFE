package dvt.jeumultijoueur;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dvt.jeuquizz.JeuQuizz;

public class JeuMultiTest {
    private static JeuMulti jm;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        jm = new JeuMulti();
        jm.init();
    }  
    
    @Test
    public void testLauch() {
        assertFalse(jm.getWin());
        assertFalse(jm.getPlay());
        jm.lauch();
        assertFalse(jm.getWin());
        assertTrue(jm.getPlay());
    } 

    @Test
    public void testaction() {
        assertFalse(jm.getControlPLayer()[2]);
        jm.action(2, true);
        assertTrue(jm.getControlPLayer()[2]);
        assertFalse(jm.getControlPLayer()[0]);
        jm.action(0, true);
        assertTrue(jm.getControlPLayer()[0]);
        jm.action(0, false);
        assertFalse(jm.getControlPLayer()[0]);
    }
    
    @Test
    public void testReset() {
        jm.reset();
        assertFalse(jm.getWin());
        assertFalse(jm.getPlay());
    }
}
