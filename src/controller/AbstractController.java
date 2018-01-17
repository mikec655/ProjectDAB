package controller;

import logic.Model;
import javax.swing.JPanel;

public abstract class AbstractController extends JPanel{
	private static final long serialVersionUID = -3853250704510233959L;
	protected Model model;
	
	public AbstractController(Model model) {
		this.model = model;
	}
}
