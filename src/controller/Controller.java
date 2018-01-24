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
	JButton plusOneMinuteButton;
	JButton plusOneHourButton;
	JButton plusOneDayButton;
	JButton plusOneWeekButton;
	JButton plusThirtyDaysButton;
	JButton plusOneYearButton;
	JSlider speedSlider;
	
	//Constructor controller
	public Controller(Model model) {
		super(model);
		startButton = new JButton("Start");
		pauseButton = new JButton("Pauze");
		resetButton = new JButton("Reset");
		plusOneMinuteButton = new JButton("+1 minute");
		plusOneHourButton= new JButton("+1 uur");
		plusOneDayButton = new JButton("+1 dag");
		plusOneWeekButton = new JButton("+1 week");
		plusThirtyDaysButton = new JButton("+30 dagen");
		plusOneYearButton = new JButton("+1 jaar");
		speedSlider = new JSlider(-3, 1, -2);
		setUpPanel();
	}
	
	//Het opzetten van de controller panel.
	private void setUpPanel(){
		setLayout(new FlowLayout());
		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		resetButton.addActionListener(this);
		plusOneMinuteButton.addActionListener(this);
		plusOneHourButton.addActionListener(this);
		plusOneDayButton.addActionListener(this);
		plusOneWeekButton.addActionListener(this);
		plusThirtyDaysButton.addActionListener(this);
		plusOneYearButton.addActionListener(this);
		speedSlider.addChangeListener(this);
		add(startButton);
		add(pauseButton);
		add(resetButton);
		add(plusOneMinuteButton);
		add(plusOneHourButton);
		add(plusOneDayButton);
		add(plusOneWeekButton);
		add(plusThirtyDaysButton);
		add(plusOneYearButton);
		add(speedSlider);
	}
	
	private int calculateTickPause() {
		return (int) Math.pow(10, speedSlider.getValue() * -1);
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
			model.reset(calculateTickPause());
		}
		if (e.getSource() == plusOneMinuteButton) {
			model.skip(1);
		}
		if (e.getSource() == plusOneHourButton) {
			model.skip(60);
		}
		
		if (e.getSource() == plusOneDayButton) {
			model.skip(24 *60);
		}
		if (e.getSource() == plusOneWeekButton) {
			model.skip(24*60*7);
		}
		if (e.getSource() == plusThirtyDaysButton) {
			model.skip(24*60*30);
		}
		if (e.getSource() == plusOneYearButton) {
			model.skip(24*60*365);
		}
		}
	
	//stateChanged voor de verandering van de sliders
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == speedSlider) {
			model.setTickPause(calculateTickPause());
		}
	}
}