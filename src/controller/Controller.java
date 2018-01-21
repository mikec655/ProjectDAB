package controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Model;

public class Controller extends AbstractController implements ActionListener, ChangeListener{
	private static final long serialVersionUID = -3877309321229212169L;
	JButton startButton;
	JButton pauseButton;
	JButton resetButton;
	JSlider speedSlider;
	
	//Constructor controller
	public Controller(Model model) {
		super(model);
		startButton = new JButton("Start");
		pauseButton = new JButton("Pauze");
		resetButton = new JButton("Reset");
		speedSlider = new JSlider(-3, 1, -2);
		setUpPanel();
	}
	
	//Het opzetten van de controller panel.
	private void setUpPanel(){
		setLayout(new FlowLayout());
		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		resetButton.addActionListener(this);
		speedSlider.addChangeListener(this);
		add(startButton);
		add(pauseButton);
		add(resetButton);
		add(speedSlider);
	}

	//actionPerformed voor de actie van de knoppen
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			model.start();
		}
		
		if (e.getSource() == pauseButton) {
			model.stop();
		}
		
		if (e.getSource() == resetButton) {
			model.reset();
		}
	}
	
	//stateChanged voor de verandering van de sliders
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == speedSlider) {
			model.setTickPause((int) Math.pow(10, speedSlider.getValue() * -1));
		}
	}
}