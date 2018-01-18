package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;

//extends maakt het window aan bij de frontend.
public class Simulator extends JFrame{
	private static final long serialVersionUID = 8060582986233007360L;
	private Model model;
	private AbstractView carParkView;
	private Controller controller;
	
//super zorgt ervoor dat het een titel krijgt, die wordt boven in het frame weergegeven.

	public Simulator() {
		super("Pakeer Garage Simulator");
		model = new Model(3, 6, 30);
		controller = new Controller(model);
		carParkView = new CarParkView(model);
		setUpFrame();
	}

//setup frame geeft een grootte aan het frame (hier komt alles in wat ingesteld moet worden binnen het frame)
	public void setUpFrame() {
		setLayout(new BorderLayout());
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		setSize(1280, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
//addcomponents daarmee kun je views en controllers toevoegen aan het frame.
	public void addComponents() {
		
	}
}
