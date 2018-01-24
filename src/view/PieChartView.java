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
        return new Dimension(300, 300);
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
		int anglePass = getAngle(model.getAmountOfPassCars());
		int angleRes = getAngle(model.getAmountOfResCars());
		
	 	g.setColor(Color.WHITE);
		g.fillArc(50, 50, 200, 200, 0, 360 );
		g.setColor(Color.RED);
		g.fillArc(50, 50, 200, 200, 90, angleAdHoc);
		g.setColor(Color.BLUE);
		g.fillArc(50, 50, 200, 200, 90 + angleAdHoc, anglePass);
		g.setColor(Color.YELLOW);
		g.fillArc(50, 50, 200, 200, 90 + angleAdHoc + anglePass, angleRes);
		g.setColor(Color.BLACK);
		g.drawArc(50, 50, 200, 200, 90, 360 + 90);
	}

				
}
