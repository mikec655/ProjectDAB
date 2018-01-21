package view;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import logic.Model;

public class PercentView extends AbstractView{
	private static final long serialVersionUID = 1337;
	
	private JLabel percentshow;
	
	public PercentView(Model model) {
		super(model);
		percentshow = new JLabel();
		percentshow.setVerticalTextPosition(JLabel.TOP);
		percentshow.setHorizontalTextPosition(JLabel.RIGHT);
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		add(percentshow);
	}
	
	public void updateView() {
		int placepercent = 1000;
		percentshow.setText(String.valueOf(placepercent));
		System.out.println(placepercent);
	}
	
	private int getNumberOfPlaces(int placepercent) {
			if (getNumberOfPlaces(placepercent) < 10) {
				placepercent += 9;
			} else {
				placepercent += 55;
			}
			return placepercent;
			
		}
		

    
}
