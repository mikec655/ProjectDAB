package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.tijdView;

//extends maakt het window aan bij de frontend.
public class Simulator extends JFrame{
	private static final long serialVersionUID = 8060582986233007360L;
	private Model model;
	private AbstractView carParkView;
	private Controller controller;
	private AbstractView tijdView;
	
//super zorgt ervoor dat het een titel krijgt, die wordt boven in het frame weergegeven.
	public Simulator() {
		super("Pakeer Garage Simulator");
		model = new Model();
		controller = new Controller(model);
<<<<<<< HEAD
		carParkView = new CarParkView(model);;
=======
		carParkView = new CarParkView(model);
		tijdView = new tijdView(model);
>>>>>>> branch 'working_branch' of https://github.com/mikec655/ProjectDAB.git
		setUpFrame();
	}

//setup frame geeft een grootte aan het frame (hier komt alles in wat ingesteld moet worden binnen het frame)
	private void setUpFrame() {
		setLayout(new BorderLayout());
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		getContentPane().add(tijdView, BorderLayout.EAST);
		setSize(1280, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
//addcomponents daarmee kun je views en controllers toevoegen aan het frame.
	public void addComponents() {
		
	}
}
