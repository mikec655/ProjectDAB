package view;

import logic.Model;
import javax.swing.JPanel;
 
public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = -6352078415307748878L;
	protected Model model;
	
	public AbstractView(Model model) {
		this.model = model;
	}
	
	public void updateView() {
		repaint();
	}
}
