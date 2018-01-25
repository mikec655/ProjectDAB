package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import logic.Model;

public class LineGraphViewProfits extends AbstractView{
	private static final long serialVersionUID = 12356535345943L;
	private ArrayList<Integer> pointsProfit;

	public LineGraphViewProfits(Model model) {
		super(model);
		pointsProfit = new ArrayList<Integer>();

	}
	
	
	//Hier word de dimension van de car gezet.
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

	public void addPoints() {
		pointsProfit.add((int)model.getProfitAv()/2);
		Iterator<Integer> it = pointsProfit.iterator();
		if (pointsProfit.size() == 295) {
			it.next();
			it.remove();
		}
	}
	
	
	//paint een circelmet daarin het percentage.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
		 	g.setColor(new Color(175, 175, 175));
			g.fillRect(0, 0, 300, 300);
			g.setColor(Color.red);
			int oldY = 0;
			double oldX = -6.0;
			for(Integer y : pointsProfit) {
				g.drawLine((int) oldX, 280 - oldY, (int) oldX + (280/1400), 280 - y);
				oldX += 1;
				oldY = y;
			}
		} catch(Exception e) {
			
		}

	}

}
