package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import logic.Model;

public class ProfitsHistogramView extends AbstractView {
	private static final long serialVersionUID = -6119168362214054592L;
	private ArrayList<Integer> pointsProfit;
	private int highestMonth;

	public ProfitsHistogramView(Model model) {
		super(model);
		pointsProfit = new ArrayList<Integer>();
		highestMonth = 1;
	}
	
	//Hier wordt de dimension van de car gezet.
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
	
	public void addProfit() {
		pointsProfit.add(model.getMonthProfit());
		highestMonth = 1;
		for (Integer money : pointsProfit) {
			if (money >= highestMonth) {
				highestMonth = money;
			}
		}
		Iterator<Integer> it = pointsProfit.iterator();
		if (pointsProfit.size() == 13) {
			it.next();
			it.remove();
		}
	}
	
	private int getSize(double money) {
		return (int) (1.0 * money / highestMonth * 284);
	}
	
	//Paint een circle met daarin het percentage.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//int sizeAdHoc = model.getNumberOfAdHocCars();
		//int sizePass = model.getNumberOfPassCars();
		//int sizeRes = model.getNumberOfResCars();
		
	 	g.setColor(Color.gray);
		g.fillRect(0, 0, 300, 300);
		
		int i = 0;
		for (Integer money : pointsProfit) {
			g.setColor(new Color(255, 0, 255));//fill
			g.fillRect(2 + 24 * i,300-getSize(money),24,money);
			g.setColor(Color.black); //draw
			g.drawRect(2 + 24 * i,300-getSize(money),24,money); //x,y,breedte,lengte
			i++;
		}
		g.setColor(Color.white);//nummer
		g.drawString("Omzet in € (" + highestMonth + ")", 1, 10);//x,y,breedte,lengte
		g.drawString("0                                               <-- Afgelopen 12 maanden", 1, 280);
	}
					
}
