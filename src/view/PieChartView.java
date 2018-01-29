package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import logic.Model;


/**
 * Hier word de PieChart beschreven en hoe je de angel uit kan rekenen.
 * @author bernt
 * @version 1.0
 *
 */
public class PieChartView extends AbstractView {
	private static final long serialVersionUID = -3842302968396266777L;
	
/**
 * Eerst word het model mee gegeven aan de PieChart.
 * @param model
 */
	public PieChartView(Model model) {
		super(model);
	}
	
	
/**
 * Hier word de Dimensie weergegeven qua lengte 300, 300
 */
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

/**
 * Hier word de int gecast omdat je gewoon een integer nodig hebt.
 * @param amountOfCars
 * @return (int) angle 
 */
	private int getAngle(int amountOfCars) {
		int total = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
		double angle =  1.0 * (double) amountOfCars / (double) total * 360.0;
		return (int) angle;
	}
	
	
/**
 * Hier word het aantal auto's opgehaald. De auto's die worden ophgehaald worden gedeeld
 * door het totaal aantal auto's En dat dan keer 360 gedaan. in getAngle. 
 * @param Graphics g
 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int angleAdHoc = getAngle(model.getNumberOfAdHocCars());
		int anglePass = getAngle(model.getNumberOfPassCars());
		int angleRes = getAngle(model.getNumberOfResCars());
		
		int ADHCAR = angleAdHoc;
		int NOPASS = anglePass;
		int NORES = angleRes;
		
	 	g.setColor(Color.WHITE);
		g.fillArc(50, 50, 200, 200, 0, 360 );
		g.drawString("4. lege plekken"+ ADHCAR , 10, 55);
		g.setColor(Color.RED);
		g.drawString("Auto's"+ ADHCAR, 10, 10);
		g.fillArc(50, 50, 200, 200, 90, angleAdHoc);
		g.setColor(Color.GREEN);
		g.fillArc(50, 50, 200, 200, 90 + angleAdHoc, anglePass);
		g.drawString("Auto's" + NOPASS, 10, 25);
		g.setColor(Color.BLUE);
		g.fillArc(50, 50, 200, 200, 90 + angleAdHoc + anglePass, angleRes);
		g.drawString("Auto's"+ NORES, 10, 40);
		g.setColor(Color.BLACK);
		g.drawArc(50, 50, 200, 200, 90, 360 + 90);
	}

				
}
