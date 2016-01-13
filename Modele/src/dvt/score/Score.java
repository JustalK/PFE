package dvt.score;

import static dvt.jeuchronometre.ConstantesJeu.CONSIGNE;

import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Score extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    private JPanel world;
    private JLabel info;
    private String score;
    
    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getForeground());
        world.setLayout(null);
        

        addControl("LEFT", new Action(this,false));
        addControl("RIGHT", new Action(this,true));  
        
        info = new JLabel("", JLabel.CENTER);
        parseXML();
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);
        
        this.add(world);
    }
    
    @Override
    public void update() {
        /** Dont need any update **/
    }

    @Override
    public void render() {
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        world.setBackground(getBackground());
    }
    
    public void parseXML() {
        File inputFile = new File("../ressources/score.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            score = "<html><center>"+doc.getDocumentElement().getNodeName();
            
            score += "<br />____________________<br />";
            score += "<table>";
            NodeList nList = doc.getElementsByTagName("joueur");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    score += "<tr><td>"+eElement.getAttribute("name")+"</td><td>&nbsp&nbsp&nbsp&nbsp&nbsp</td><td>"+eElement.getElementsByTagName("score").item(0).getTextContent()+"</td></tr>";
                }
            }
            
            score +="</table></center></html>";
            
            info.setText(score);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void top() {
        
    }
    
    public void down() {
        
    }
    
    @Override
    public void reset() {
       
    }

    public void valid() {
        
    }
}
