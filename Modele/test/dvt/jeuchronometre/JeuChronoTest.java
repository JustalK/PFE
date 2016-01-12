package dvt.jeuchronometre;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class JeuChronoTest {
    private static JeuChrono jc;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        jc = new JeuChrono();
        jc.init();
    }    
    
    @Test
    public void testReset() {
        assertFalse(jc.getPressed());
        jc.action(false);
        assertTrue(jc.getPressed());
    }
    
    @Test
    public void testAction() {
        jc.reset();
        assertTrue(jc.getCount()==0);
        assertTrue(jc.getPressed());
    }

}
