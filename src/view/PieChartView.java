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

	private double getAngle() {
		double placepercentpie;
		double tempspotspie = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
		placepercentpie = 1.0 * model.getNumberOfOpenSpots() / tempspotspie * 100.00;
		placepercentpie = Math.round(placepercentpie * 10.00);
		placepercentpie = (placepercentpie / 10.00) * 3.60;
		return placepercentpie;
		
}
	
	
	//paint een circelmet daarin het percentage.
		 public void paintComponent(Graphics g) {
			super.paintComponent(g);
		 	g.setColor(Color.white);
			g.fillArc(25, 25, 200, 200, 0, (int) (100*3.60));
			g.setColor(Color.red);
			g.fillArc(25, 25, 200, 200, 0, (int) (getAngle()));  
			}

				
}
