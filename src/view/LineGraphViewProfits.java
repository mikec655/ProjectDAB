package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import logic.Model;

//LineGraphViewProfits extends AbstractView.
public class LineGraphViewProfits extends AbstractView{
	private static final long serialVersionUID = 12356535345943L;
	private ArrayList<Integer> pointsProfit;

	//Hier wordt de super aangeroepen van de klasse AbstractView.
	public LineGraphViewProfits(Model model) {
		super(model);
		pointsProfit = new ArrayList<Integer>();

	}
	
	
	//Hier wordt de dimension van de car gezet.
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

	//Voegt de points toe wat nodig is voor de profit van de LineGraph.
	public void addPoints() {
		pointsProfit.add((int)model.getProfitAv()/2);
		Iterator<Integer> it = pointsProfit.iterator();
		if (pointsProfit.size() == 295) {
			it.next();
			it.remove();
		}
	}
	
	
	//paint een circle met daarin het percentage moet hoeveel profit er is gemaakt.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
		 	g.setColor(new Color(175, 175, 175));
			g.fillRect(0, 0, 300, 300);
			int oldY = 0;
			double oldX = -6.0;
			for(Integer y : pointsProfit) {
				g.setColor(Color.red);
				g.drawLine((int) oldX, 280 - oldY, (int) oldX + (280/1400), 280 - y);
				oldX += 1;
				oldY = y;
				
			}
			g.setColor(Color.black);
			g.drawString("0                                                                     <--Afgelopen 24 uur ",0,300);
			g.drawString("avgprofit", 0, 30);
			g.drawString("500",0,10);
		} catch(Exception e) {
		}

	}

}
