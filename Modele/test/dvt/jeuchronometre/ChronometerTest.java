package dvt.jeuchronometre;

import org.junit.BeforeClass;
import org.junit.Test;

public class ChronometerTest {
    private static Chronometer ch;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        ch = new Chronometer();     
    }    
    
    @Test
    public void test() {
        ch.start();
    }

}
