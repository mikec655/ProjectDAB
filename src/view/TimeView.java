package view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import logic.Model;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.*;

public class TimeView extends AbstractView{
	private static final long serialVersionUID = 1;
	private Border blackline;
	private JLabel tijdshow;
	
	public TimeView(Model model) {
		super(model);
		
		blackline = BorderFactory.createLineBorder(Color.black, 1 );
		tijdshow = new JLabel();
		tijdshow.setVerticalTextPosition(JLabel.BOTTOM);
		tijdshow.setHorizontalTextPosition(JLabel.CENTER);
		tijdshow.setOpaque(true);
		tijdshow.setBackground(Color.WHITE);
		tijdshow.setBorder(new CompoundBorder((blackline),BorderFactory.createEmptyBorder(20,20,20,20)));
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		add(tijdshow);
	}
	
	public void updateView() {
		String timeString = "";
		timeString += getClockString(model.getHour(), model.getMinute()) + " ";
		timeString += getDayString(model.getDayOfWeek()) + " ";
		timeString += model.getDay() + " ";
		timeString += getMonthString(model.getMonth()) + " "; 
		timeString += model.getYear();
		tijdshow.setText(timeString);
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
			case 1 : return "zondag";
			case 2 : return "maandag";
			case 3 : return "dinsdag";
			case 4 : return "woensdag";
			case 5 : return "donderdag";
			case 6 : return "vrijdag";
			case 7 : return "zaterdag";
			default: return "";
		}
	}
	
	private String getMonthString(int month) {
		switch(month) {
			case 0 : return "januari";
			case 1 : return "februari";
			case 2 : return "maart";
			case 3 : return "april";
			case 4 : return "mei";
			case 5 : return "juni";
			case 6 : return "juli";
			case 7 : return "augustus";
			case 8 : return "september";
			case 9 : return "oktober";
			case 10 : return "november";
			case 11 : return "december";
			default: return "";
		}
	}
}