package main;

import javax.swing.JFrame;

public class Simulator extends JFrame{
	private static final long serialVersionUID = 8060582986233007360L;

	public Simulator() {
		super("Pakeer Garage Simulator");
		setUpFrame();
	}
	
	public void setUpFrame() {
		setSize(640, 320);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void addComponents() {
		
	}
}
