package view;

import javax.swing.JLabel;
import logic.Model;
import java.awt.FlowLayout;

public class TimeView extends AbstractView{
	private static final long serialVersionUID = 1;
	
	private JLabel tijdshow;
	
	public TimeView(Model model) {
		super(model);
		tijdshow = new JLabel();
		tijdshow.setVerticalTextPosition(JLabel.BOTTOM);
		tijdshow.setHorizontalTextPosition(JLabel.CENTER);
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		add(tijdshow);
	}
	
	public void updateView() {
		tijdshow.setText("Jaar: " + model.getYear() + " Dag: " + getDayString(model.getWeekday()) + " Tijd: " + getClockString(model.getHour(), model.getMinute()));
	}
	
	private String getClockString(int hours, int minutes) {
		String clockString = "";
		if (model.getHour() < 10) {
			clockString += "0" + model.getHour();
		} else {
			clockString += model.getHour();
		}
		clockString += ":";
		if (model.getMinute() < 10) {
			clockString += "0" + model.getMinute();
		} else {
			clockString += model.getMinute();
		}
		return clockString;
	}
	
	private String getDayString(int day) {
		switch(day) {
			case 0 : return "maandag";
			case 1 : return "dinsdag";
			case 2 : return "woensdag";
			case 3 : return "donderdag";
			case 4 : return "vrijdag";
			case 5 : return "zaterdag";
			default: return "zondag";
		}
	}
}