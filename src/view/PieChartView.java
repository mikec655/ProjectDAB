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
	//De
	public void updateView() {

	}
	//paint een circelmet daarin het percentage.
	 public void paintComponent(Graphics g) {
	 	g.setColor(Color.RED);
		g.fillArc(25, 25, 200, 200, 0, 45);
		g.setColor(Color.BLUE);
		g.fillArc(25, 25, 200, 200, 45, 360-45);
		g.setColor(Color.YELLOW);
		g.fillArc(25, 25, 200, 200, 45, 360-90);
    }
	 

}
