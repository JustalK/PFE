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
    }    
    
    @Test
    public void testSIVOXDevintDefault() {
        Assert.assertTrue(sd.getProsodie()==3);
    } 
    
    @Test
    public void testPlayText() {
    	sd.playText("aaa");
    	File file = new File(System.getProperty("java.io.tmpdir")+"phrase96321.wav");
    	System.out.println(System.getProperty("java.io.tmpdir")+"96321.wav");
    	if(!file.exists()) {
    		Assert.fail();
    	}
    }  

}
