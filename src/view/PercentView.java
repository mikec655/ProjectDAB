package view;

import javax.swing.JLabel;
import logic.Model;
import java.awt.FlowLayout;

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
		double placepercent = 0;
		
		placepercent = getNumberOfPlaces(placepercent);
		percentshow.setText(String.valueOf(placepercent));
	}
	
	private double getNumberOfPlaces(double placepercent) {

				double tempplacepercent2 = (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
				placepercent += model.getNumberOfOpenSpots() / tempplacepercent2 * 100.0;
				placepercent = Math.round(placepercent * 100);
				placepercent = placepercent/100;
				return placepercent;
			
		}
		

    
}
