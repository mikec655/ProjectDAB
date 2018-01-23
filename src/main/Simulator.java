package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.TimeView;
import view.Labels;
import view.PercentView;
import view.PieChartView;

//extends maakt het window aan bij de frontend.
public class Simulator extends JFrame implements ComponentListener{
	private static final long serialVersionUID = 8060582986233007360L;
	private Model model;
	private AbstractView carParkView;
	private AbstractView tijdView;
	private AbstractView labels;
	private Controller controller;
	private AbstractView percentView;
	private AbstractView pieChartView;
	private JPanel viewPanel;
	
	
	//super zorgt ervoor dat het een titel krijgt, die wordt boven in het frame weergegeven.
	public Simulator() {
		super("Pakeer Garage Simulatie");
		model = new Model();
		controller = new Controller(model);
		viewPanel = new JPanel();
		carParkView = new CarParkView(model);
		tijdView = new TimeView(model);
		labels = new Labels(model);
		percentView = new PercentView(model);
		pieChartView = new PieChartView(model);
		setUpFrame();
	}

	//setup frame geeft een grootte aan het frame (hier komt alles in wat ingesteld moet worden binnen het frame)
	private void setUpFrame() {
		setLayout(new BorderLayout());
		viewPanel.setLayout(new GridLayout(0, 1));
		addComponents();
		setIcon();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentListener(this);
		setVisible(true);
	}
	
	private void setIcon() {
		try {
			InputStream stream = Simulator.class.getClassLoader().getResourceAsStream("images/icon.png");
			BufferedImage icon = ImageIO.read(stream);
			setIconImage(icon);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	//addcomponents daarmee kun je views en controllers toevoegen aan het frame.
	public void addComponents() {
		getContentPane().add(viewPanel, BorderLayout.EAST);
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		getContentPane().add(tijdView, BorderLayout.NORTH);
		getContentPane().add(labels, BorderLayout.WEST);
		viewPanel.add(percentView);
		viewPanel.add(pieChartView);
		pack();
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
