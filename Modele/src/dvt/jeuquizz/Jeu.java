package dvt.jeuquizz;

import static dvt.menu.ConstantesMenu.BORDURE_SELECTED_DEFAULT;
import static dvt.menu.ConstantesMenu.BORDURE_SIZE_UNSELECTED_DEFAULT;
import static dvt.menu.ConstantesMenu.MARGE_LEFT_RIGHT;
import static dvt.menu.ConstantesMenu.MARGE_TOP_BOT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Jeu extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    private JPanel world;
    private GridBagConstraints c;
    private JLabel question;
    private JButton button1,button2,button3;
    private static final String[] QUESTIONS = {"Combien font 4*4 ?", "Combien font 30-5 ?", "Combien font 5+5 ?" };
    private static final int[] ANSWERS = {16,25,10};
    
    @Override
    public void init() {
        world = new JPanel();
        c = new GridBagConstraints();
        
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render() {
        question.setFont(getFont());
        button1.setFont(getFont());
        button1.setBorder(new LineBorder(BORDURE_SELECTED_DEFAULT,BORDURE_SIZE_UNSELECTED_DEFAULT));
        button1.setBackground(getButtonUnselectedBackground());
        button1.setForeground(getButtonUnselectedForeground());
        button2.setFont(getFont());
        button2.setBorder(new LineBorder(BORDURE_SELECTED_DEFAULT,BORDURE_SIZE_UNSELECTED_DEFAULT));
        button2.setBackground(getButtonUnselectedBackground());
        button2.setForeground(getButtonUnselectedForeground());
        button3.setFont(getFont());
        button3.setBorder(new LineBorder(BORDURE_SELECTED_DEFAULT,BORDURE_SIZE_UNSELECTED_DEFAULT));
        button3.setBackground(getButtonUnselectedBackground());
        button3.setForeground(getButtonUnselectedForeground());
        world.setBackground(getBackground());
        
    }

    @Override
    public void reset() {
       int random = (int)(Math.random() * QUESTIONS.length);
       question.setText(QUESTIONS[random]);
       button1.setText(String.valueOf(ANSWERS[random]));
       button2.setText(String.valueOf(ANSWERS[(random+1) % ANSWERS.length]));
       button3.setText(String.valueOf(ANSWERS[(random+2) % ANSWERS.length]));
    }

}
