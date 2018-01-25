package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import logic.Model;

public class LineGraphView extends AbstractView{
	private static final long serialVersionUID = 7382476374169229440L;
	private ArrayList<Integer> pointsAdHoc;
	private ArrayList<Integer> pointsPass;
	private ArrayList<Integer> pointsRes;

	public LineGraphView(Model model) {
		super(model);
		pointsAdHoc = new ArrayList<Integer>();
		pointsPass = new ArrayList<Integer>();
		pointsRes = new ArrayList<Integer>();
	}
	
	
	//Hier word de dimension van de car gezet.
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

	public void addPoints() {
		pointsAdHoc.add(model.getAmountOfAdHocCars());
		pointsPass.add(model.getAmountOfPassCars());
		pointsRes.add(model.getAmountOfResCars());
		Iterator<Integer> it = pointsAdHoc.iterator();
		if (pointsAdHoc.size() == 295) {
			it.next();
			it.remove();
		}
		it = pointsPass.iterator();
		if (pointsPass.size() == 295) {
			it.next();
			it.remove();
		}
		it = pointsRes.iterator();
		if (pointsRes.size() == 295) {
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
			for(Integer y : pointsAdHoc) {
				g.drawLine((int) oldX, 280 - oldY, (int) oldX + (280/1400), 280 - y);
				oldX += 1;
				oldY = y;
			}
			g.setColor(Color.blue);
			oldY = 0;
			oldX = -6.0;
			for(Integer y : pointsPass) {
				g.drawLine((int) oldX, 280 - oldY, (int) oldX + (280/1400), 280 - y);
				oldX += 1;
				oldY = y;
			}
			g.setColor(Color.yellow);
			oldY = 0;
			oldX = -6.0;
			for(Integer y : pointsRes) {
				g.drawLine((int) oldX, 280 - oldY, (int) oldX + (280/1400), 280 - y);
				oldX += 1;
				oldY = y;
			}
		} catch(Exception e) {
			
		}

	}

}
