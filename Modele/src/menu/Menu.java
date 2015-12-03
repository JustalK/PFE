package menu;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import devint.Fenetre;
import jeu.Moteur;
import listener.CloseListener;
import listener.LauchGameListener;

public class Menu extends Fenetre implements ConstantesMenu {
    private static final long serialVersionUID = 1L;
    private boolean play;
    
    private GridBagConstraints c = new GridBagConstraints(); 
    private JPanel menu = new JPanel(); 
    private int countMenu = 0;
    private int menuSelected = 0;
    
    private List<JButton> listeBoutton;
    private JLabel title;
    
    public Menu() {
    	this.play = false;
    	listeBoutton = new ArrayList<JButton>();
    	
		GridBagLayout layoutMenu = new GridBagLayout();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(MARGE_TOP_BOT,MARGE_LEFT_RIGHT,MARGE_TOP_BOT,MARGE_LEFT_RIGHT);
		menu.setLayout(layoutMenu);	
		
		addLabel(TITLE_GAME);
    	addMenu("Jouer",new LauchGameListener(this));
    	addMenu("Option",new LauchGameListener(this));
    	addMenu("Quitter",new CloseListener(this));
    	
    	this.add(menu);

    	addControl("DOWN",new DownAction(this));
    	addControl("UP",new UpAction(this));
    	
        // Affiche la fenetre
        this.setVisible(true);
        
		menuSelected = 0;
		selectedButton(listeBoutton.get(menuSelected));
    }

    public void loop() {
        while(this.isDisplayable()) {
        	render();
        	if(play) {
            	new Moteur();
	        	this.play = false;
        	}
        }   
    }
    
    private void render() {
    	menu.setBackground(getBackground());		
    	this.title.setFont(getFont());
    	this.title.setForeground(getForeground());
    	
    	int tmp = menuSelected % listeBoutton.size();
    	for(int i=0; i<listeBoutton.size();i++) {
    		if(i==tmp) {
    			selectedButton(listeBoutton.get(i));
    		} else {
    			unselectedButton(listeBoutton.get(i));
    		}
    	}
    }
    
	private void addLabel(String title) {
		this.title = new JLabel(title.toUpperCase(),JLabel.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;      
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = countMenu;
    	c.gridwidth = 3;		
    	menu.add(this.title, c);
    	
    	countMenu++;
    }
    
    private void addMenu(String title,ActionListener action) {
    	if(title!=null) {
			JButton button = new JButton(title.toUpperCase());
			button.addActionListener(action);
			unselectedButton(button);
	        c.fill = GridBagConstraints.BOTH;
	        c.ipady = 100;  
	        c.weightx = 1.0;
	        c.weighty = 1.0;
	        c.gridx = 0;
	        c.gridy = countMenu;
	    	c.gridwidth = 3;		
	    	menu.add(button, c);
	    	listeBoutton.add(button);
	    	countMenu++;
    	}
    }

	public void lauch() {
    	this.play = true;
	}
	
	public void down() {
		unselectedButton(listeBoutton.get(menuSelected++ % listeBoutton.size()));
		selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
		this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText());
	}

	public void up() {
		unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
		menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1) : menuSelected - 1;
		selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
		this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText());
	}	
	
	private void unselectedButton(JButton button) {
		button.setFont(getFont());
		button.setBorder(new LineBorder(BORDURE_SELECTED_DEFAULT,BORDURE_SIZE_UNSELECTED_DEFAULT));
		button.setBackground(getButtonUnselectedBackground());
		button.setForeground(getButtonUnselectedForeground());	
	}
	
	private void selectedButton(JButton button) {
		button.setFont(getFont().deriveFont(TAILLE_SELECTED_DEFAULT));
		button.setBorder(new LineBorder(BORDURE_UNSELECTED_DEFAULT ,BORDURE_SIZE_SELECTED_DEFAULT));
		button.setBackground(getButtonSelectedBackground());
		button.setForeground(getButtonSelectedForeground());
		this.getRootPane().setDefaultButton(button);
	}
	
}
