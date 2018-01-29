package view;

import javax.swing.JLabel;
import logic.Model;
import java.awt.FlowLayout;
import java.awt.Font;

//TimeView extends AbstractView.
public class TimeView extends AbstractView{
	private static final long serialVersionUID = 1;
	private JLabel tijdshow;

	//Hier wordt de super aangeroepen van de klasse AbstractView.
	public TimeView(Model model) {
		super(model);
		tijdshow = new JLabel();
		tijdshow.setVerticalTextPosition(JLabel.BOTTOM);
		tijdshow.setHorizontalTextPosition(JLabel.CENTER);
		tijdshow.setOpaque(true);
		setUpPanel();
	}
	
	//Hier kan je de panel van de FlowLayout mee zien, er wordt ook een tijdshow toegevoegd.
	private void setUpPanel(){
		setLayout(new FlowLayout());
		tijdshow.setFont(new Font(null, Font.PLAIN, 24));
		add(tijdshow);
	}
	
	//Update de view waarin je de minuut, uur, dag, week, maand en jaar zit dat je op het moment hebt.
	public void updateView() {
		String timeString = "";
		timeString += getClockString(model.getHour(), model.getMinute()) + " ";
		timeString += getDayString(model.getDayOfWeek()) + " ";
		timeString += model.getDay() + " ";
		timeString += getMonthString(model.getMonth()) + " "; 
		timeString += model.getYear();
		tijdshow.setText(timeString);
	}
	
	//Hier wordt de string van de klok aangemaakt voor de uren en minuten die je kan zien.
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
	
	//Switchcase voor de dagen in de week.
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
	//Switchcase voor de maanden in een jaar.
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
