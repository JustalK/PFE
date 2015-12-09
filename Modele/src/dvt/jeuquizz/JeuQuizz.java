package dvt.jeuquizz;

import static dvt.menu.ConstantesMenu.MARGE_LEFT_RIGHT;
import static dvt.menu.ConstantesMenu.MARGE_TOP_BOT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dvt.jeuquizz.Action;

public class JeuQuizz extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    private JPanel world;
    private GridBagConstraints c;
    private JLabel question;
    private JButton button1,button2,button3;
    private static final String[] QUESTIONS = {"Combien font 4*4 ?", "Combien font 30-5 ?", "Combien font 5+5 ?" };
    private static final int[] ANSWERS = {16,25,10};
    private int random;
    private int choix;
    private boolean valid;
    
    @Override
    public void init() {
        world = new JPanel();
        c = new GridBagConstraints();
        

        addControl("LEFT", new Action(this,false));
        addControl("RIGHT", new Action(this,true));
        addControl("ENTER", new EnterAction(this));
        
        GridBagLayout layoutMenu = new GridBagLayout();
        world.setLayout(layoutMenu);
        c.insets = new Insets(MARGE_TOP_BOT, MARGE_LEFT_RIGHT, MARGE_TOP_BOT,MARGE_LEFT_RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;     
        
        button1 = new JButton("16");
        button1.setFont(getFont());
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        world.add(button1,c);

        button2 = new JButton("10");
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        world.add(button2,c);        
  
        button3 = new JButton("25");
        button3.setFont(getFont());
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        world.add(button3,c);         
        
        question = new JLabel("Combien font 4*4 ?", JLabel.CENTER);
        question.setBackground(getForeground());
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.ipady = 500;
        c.gridx = 0;
        c.gridy = 0;
        world.add(question,c);   
        
        this.add(world);
    }
    
    @Override
    public void update() {
        /** Dont need any update **/
    }

    @Override
    public void render() {
        try {
            if(valid && choix == random) {
                question.setText("BONNE REPONSE !");
                Thread.sleep(1000);
                reset();
            } else if(valid) {
                question.setText("MAUVAISE REPONSE !");
                Thread.sleep(1000);
                reset();
            } else {
                question.setText(QUESTIONS[random]);
            }
            question.setFont(getFont());
            world.setBackground(getBackground());            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void left() {
        if(choix == 1) {
            unselectedButton(button2);
            choix--;
            selectedButton(button1);
        } else if(choix == 2) {
            unselectedButton(button3);
            choix--;
            selectedButton(button2);
        }        
    }
    
    public void right() {
        if(choix == 0) {
            unselectedButton(button1);
            choix++;
            selectedButton(button2);
        } else if(choix == 1) {
            unselectedButton(button2);
            choix++;
            selectedButton(button3);
        }
    }
    
    @Override
    public void reset() {
       random = (int)(Math.random() * QUESTIONS.length);
       question.setText(QUESTIONS[random]);
       button1.setText(String.valueOf(ANSWERS[0]));
       button2.setText(String.valueOf(ANSWERS[1]));
       button3.setText(String.valueOf(ANSWERS[2]));
       choix = 0;
       selectedButton(button1);
       unselectedButton(button2);
       unselectedButton(button3);
       valid = false;
    }

    public void valid() {
        valid = true;
    }

}