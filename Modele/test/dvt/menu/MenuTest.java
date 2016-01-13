package dvt.menu;

import static org.junit.Assert.*;

import java.awt.GridBagConstraints;

import org.junit.BeforeClass;
import org.junit.Test;

public class MenuTest {
    private static Menu menu;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        menu = new Menu();
    }  
    
    @Test
    public void testMenu() {
        assertEquals(100,menu.getC().ipady);
        assertEquals(3,menu.getC().gridwidth);
        assertEquals(GridBagConstraints.BOTH,menu.getC().fill);
    }
    
    @Test
    public void testDown() {
        for(int i=0;i<8;i++) {
            assertTrue(menu.getMenuSelected()==i);
            menu.down();
        }
    }
    
    @Test
    public void testAddMenu() {
        menu.addMenu("tmp",new Action(menu,0));
        assertTrue(1.0==menu.getC().weightx);
        assertTrue(1.0==menu.getC().weighty);
        assertTrue(6==menu.getC().gridy);  
    }
    
    @Test
    public void testAddLabel() {
        menu.addLabel("tmp");
        assertTrue(1.0==menu.getC().weightx);
        assertTrue(1.0==menu.getC().weighty);
        assertTrue(7==menu.getC().gridy);
    }
    
    @Test
    public void test1() {}

    @Test
    public void test2() {}

    @Test
    public void test3() {}

    @Test
    public void test4() {}

    @Test
    public void test5() {}

    @Test
    public void test6() {}

    @Test
    public void test7() {}

    @Test
    public void test8() {}
    
    @Test
    public void testUp() {
        for(int i=8;i>0;i--) {
            assertTrue(menu.getMenuSelected()==i);
            menu.up();
        }
    }

}
