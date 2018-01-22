package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.TimeView;
import view.PercentView;
import view.PieChartView;

//extends maakt het window aan bij de frontend.
public class Simulator extends JFrame implements ComponentListener{
	private static final long serialVersionUID = 8060582986233007360L;
	private Model model;
	private AbstractView carParkView;
	private AbstractView tijdView;
	private Controller controller;
	private AbstractView percentView;
	private AbstractView pieChartView;
	private JPanel viewPanel;
	
	
	//super zorgt ervoor dat het een titel krijgt, die wordt boven in het frame weergegeven.
	public Simulator() {
		super("Pakeer Garage Simulator");
		model = new Model();
		controller = new Controller(model);
		viewPanel = new JPanel();
		carParkView = new CarParkView(model);
		tijdView = new TimeView(model);
		percentView = new PercentView(model);
		pieChartView = new PieChartView(model);
		setUpFrame();
	}

	//setup frame geeft een grootte aan het frame (hier komt alles in wat ingesteld moet worden binnen het frame)
	private void setUpFrame() {
		setLayout(new BorderLayout());
		viewPanel.setLayout(new GridLayout(0, 1));
		addComponents();
		addComponentListener(this);
		setSize(1280, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//addcomponents daarmee kun je views en controllers toevoegen aan het frame.
	public void addComponents() {
		getContentPane().add(viewPanel, BorderLayout.EAST);
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		getContentPane().add(tijdView, BorderLayout.NORTH);
		viewPanel.add(percentView, BorderLayout.EAST);
		viewPanel.add(pieChartView, BorderLayout.EAST);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		model.notifyViews();
	}
	
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
