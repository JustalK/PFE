package dvt.jeuquizz;

import static org.junit.Assert.*;

import java.awt.GridBagConstraints;

import org.junit.BeforeClass;
import org.junit.Test;

import dvt.menu.Menu;

public class JeuQuizzTest {
    private static JeuQuizz jq;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        jq = new JeuQuizz();
        jq.init();
    }  
    
    @Test
    public void testReset() {
        assertEquals(jq.getChoice(),0);
        assertFalse(jq.getValid());
    }    
    
    @Test
    public void testLeftAndRight() {
        assertEquals(jq.getChoice(),0);
        jq.right();
        assertEquals(jq.getChoice(),1);
        jq.right();
        assertEquals(jq.getChoice(),2);
        jq.right();
        assertEquals(jq.getChoice(),2);
        jq.left();
        assertEquals(jq.getChoice(),1);
        jq.left();
        assertEquals(jq.getChoice(),0);
        jq.left();
        assertEquals(jq.getChoice(),0);
    }
    
}
