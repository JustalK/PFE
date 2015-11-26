package t2s.son;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// https://ipv4.google.com/sorry/IndexRedirect?continue=https://translate.google.com/translate_tts%253F%25253Ftl%25253DFR%252526q%25253DJe%25252Bsuis%25252Bune%25252Blegende%2526q%253DCGMSBFbLY6wYrLTdsgUiGQDxp4NLv8O94PK0sb1yoMZeDvOW-k6-vIQ%26q%3DCGMSBFbLY6wYmbfdsgUiGQDxp4NL5x94CYyZwsxg3hNoq9RUDqjJUXk&q=CGMSBFbLY6wYvrfdsgUiGQDxp4NL0P6CwJ6lw-pa8iSwgFR0De5IW_4
// https://translate.google.com/translate_tts?tl=fr&q=Je%20suis%20une%20legendes&ie=UTF-8&total=1&idx=0&client=t
public class SimpleTextToSpeech {
    private static final String TEXT_TO_SPEECH_SERVICE = 
            "https://translate.google.com/translate_tts";
    private static final String USER_AGENT =  
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:11.0) " +
            "Gecko/20100101 Firefox/11.0";

    public void go(String text) throws Exception {
        // Create url based on input params
        String strUrl = TEXT_TO_SPEECH_SERVICE + "?" + 
                "tl=fr&q=" + text +"&ie=UTF-8&total=1&idx=0&client=t";
        URL url = new URL(strUrl);

        // faire une connection via HTTPURL via le protocole GET
        // Le User-Agent permet d'imiter le comportement d'un navigateur
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", USER_AGENT);
        connection.connect();

        // Obtenir le content
        BufferedInputStream bufIn = 
                new BufferedInputStream(connection.getInputStream());
        byte[] buffer = new byte[1024];
        int n;
        ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
        while ((n = bufIn.read(buffer)) > 0) {
            bufOut.write(buffer, 0, n);
        }

        // Done, save data
        File output = new File("output.mp3");
        BufferedOutputStream out = 
                new BufferedOutputStream(new FileOutputStream(output));
        out.write(bufOut.toByteArray());
        out.flush();
        out.close();
    }
}
