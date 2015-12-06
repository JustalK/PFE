package menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Action extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private transient Menu menu;
	private int choice;
	
	public Action(Menu menu,int choice) {
		this.menu=menu;
		this.choice=choice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		menu.chooseChoice(choice);
	}
}
