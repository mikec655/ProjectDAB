package view;

import logic.Model;
import javax.swing.JPanel;
 
//Abstract extends JPanel.
public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = -6352078415307748878L;
	protected Model model;
	
	//Voegt een view toe aan het model.
	public AbstractView(Model model) {
		this.model = model;
		model.addView(this);
	}
	
	//Update de view voor het model.
	public void updateView() {
		repaint();
	}
	
	//Voegt de points.
	public void addPoints() {
		
	}
}
