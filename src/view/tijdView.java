package view;

import javax.swing.JLabel;
import logic.Model;
import java.awt.FlowLayout;

public class tijdView extends AbstractView{
	private static final long serialVersionUID = 1;
	
	private JLabel tijdshow;
	
	public tijdView(Model model) {
		super(model);
		tijdshow = new JLabel();
		tijdshow.setVerticalTextPosition(JLabel.BOTTOM);
		tijdshow.setHorizontalTextPosition(JLabel.CENTER);
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		add(tijdshow);
		updatetime();
	}
	
	private void updatetime() {
		tijdshow.setText("dag:"+ model.getDay() + "uur"+ model.getHour()+ "minuut"+ model.getMinute() );
	}
	
	public void  updateView() {
		updatetime();
		repaint();
	}
	
}