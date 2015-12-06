package dvt.run;

import java.net.MalformedURLException;

import dvt.menu.Menu;

public final class Main {

    private Main() {
    }

    public static void main(String[] arg) throws MalformedURLException {
        Menu menu = new Menu();
        menu.loop();
    }
}
