package view;
//berry test0
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import logic.Model;

public class PieChartView extends AbstractView {
	private static final long serialVersionUID = -3842302968396266777L;

	public PieChartView(Model model) {
		super(model);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }

	public void updateView() {

	}
	
	 public void paintComponent(Graphics g) {
	 	g.setColor(Color.RED);
		g.fillArc(25, 25, 200, 200, 0, 45);
		g.setColor(Color.BLUE);
		g.fillArc(25, 25, 200, 200, 45, 360-45);
		g.setColor(Color.YELLOW);
		g.fillArc(25, 25, 200, 200, 45, 360-90);
    }
	 

}
