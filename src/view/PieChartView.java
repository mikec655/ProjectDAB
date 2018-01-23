package view;
//color draw a line or circel
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import logic.Model;


//PieChartView extends AbstractView 
public class PieChartView extends AbstractView {
	private static final long serialVersionUID = -3842302968396266777L;
	
//Hier word de super aangeroepen van de klasse AbstractView.
	public PieChartView(Model model) {
		super(model);
	}
	
	
	//Hier word de dimension van de car gezet.
	public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }

	private int getAngle(int amountOfCars) {
		int total = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
		double angle =  1.0 * (double) amountOfCars / (double) total * 360.0;
		//System.out.println("angel: " + angle);
		return (int) angle;
	}
	
	
	//paint een circelmet daarin het percentage.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int angleAdHoc = getAngle(model.getAmountOfAdHocCars());
		//int anglePass = getAngle(model.getAmountOfPassCars());
		int angleRes = getAngle(model.getAmountOfResCars());
		int angleOpen = getAngle(model.getNumberOfOpenSpots());
		
	 	g.setColor(Color.BLUE);
		g.fillArc(25, 25, 200, 200, 90, 360 + 90);
		g.setColor(Color.RED);
		g.fillArc(25, 25, 200, 200, 90, angleAdHoc);
		g.setColor(Color.YELLOW);
		g.fillArc(25, 25, 200, 200, 90 + angleAdHoc, angleRes);
		g.setColor(Color.WHITE);
		g.fillArc(25, 25, 200, 200, 90 + angleAdHoc + angleRes, angleOpen);
	}

				
}
