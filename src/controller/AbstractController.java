package controller;

import logic.Model;
import javax.swing.JPanel;

//je stopt hier de algemene " regels " in die voor iedere controller geldt.
public abstract class AbstractController extends JPanel{
	private static final long serialVersionUID = -3853250704510233959L;
	protected Model model;
	
	public AbstractController(Model model) {
		this.model = model;
	}
}
