package view;
//color draw a line or circel
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import logic.Model;


//PieChartView extends AbstractView 
public class HistogramView extends AbstractView {
	private static final long serialVersionUID = -3842302968396266777L;
	
//Hier word de super aangeroepen van de klasse AbstractView.
	public HistogramView(Model model) {
		super(model);
	}
	
	
	//Hier word de dimension van de car gezet.
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

	private int getSize(int amountOfCars) {
		int total = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
		double size =  1.0 * (double) amountOfCars;
		//System.out.println("angel: " + angle);
		return (int) size;
	}
	
	
	//paint een circelmet daarin het percentage.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int sizeAdHoc = getSize(model.getAmountOfAdHocCars());
		int sizePass = getSize(model.getAmountOfPassCars());
		int sizeRes = getSize(model.getAmountOfResCars());
		
	 	g.setColor(Color.white);
		g.fillRect(0, 0, 300, 300);
		
		g.setColor(Color.red);
		g.fillRect(25,284-sizeAdHoc,50,sizeAdHoc);
		g.setColor(Color.black);
		g.drawString("" + model.getAmountOfAdHocCars(), 37, 283-sizeAdHoc);//x,y,breedte,lengte
		
		g.setColor(Color.yellow);
		g.fillRect(125,284-sizeRes,50,sizeRes); //x,y,breedte,lengte
		g.setColor(Color.black);
		g.drawString("" + model.getAmountOfResCars(), 137, 283-sizeRes);//x,y,breedte,lengte
		
		g.setColor(Color.blue);
		g.fillRect(225,284-sizePass,50,sizePass); //x,y,breedte,lengte
		g.setColor(Color.black);
		g.drawString("" + model.getAmountOfPassCars(), 237, 283-sizePass);//x,y,breedte,lengte
		
		

	}

				
}
