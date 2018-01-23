package controller;

import logic.Model;
import javax.swing.JPanel;
//Dit is dus de Super klasse. 
//je stopt hier de algemene " regels " in die voor iedere controller geldt.
public abstract class AbstractController extends JPanel{
	private static final long serialVersionUID = -3853250704510233959L;
	protected Model model;
	// Deze maakt de model aan en zorgt er voor dat de model in in this.model komt te staan.
	public AbstractController(Model model) {
		this.model = model;
	}
}
