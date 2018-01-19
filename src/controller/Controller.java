package controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import logic.Model;

public class Controller extends AbstractController implements ActionListener{
	private static final long serialVersionUID = -3877309321229212169L;
	//start en stop knoppen
	JButton startButton;
	JButton stopButton;
	
	//hier wordt de super aangeroepen en het model doorgegeven.
	public Controller(Model model) {
		super(model);
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		setUpPanel();
	}
	
	//flowlayout voegt de knoppen toe aan het panel.
	private void setUpPanel(){
		setLayout(new FlowLayout());
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		add(startButton);
		add(stopButton);
	}

	//functionaliteit van de start en stop knoppen.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			model.start();
		}
		
		if (e.getSource() == stopButton) {
			model.stop();
		}
		
	}
}