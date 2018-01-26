package view;

import javax.swing.JLabel;
import logic.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.border.*;

//Labels extends AbstractView.
public class Labels extends AbstractView{
	private static final long serialVersionUID = 1;
	private JLabel labels;
	
	//Hier wordt de super aangeroepen van de klasse AbstractView.
	public Labels(Model model) {
		super(model);
		labels = new JLabel();
		labels.setVerticalTextPosition(JLabel.BOTTOM);
		labels.setHorizontalTextPosition(JLabel.CENTER);
		setUpPanel();
	}
	
	//Hier kan je de panel van de FlowLayout mee zien, wordt ook labels bij toegevoegd.
	private void setUpPanel(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(labels);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
	
	//Update de view waarbij je een legenda kan zien waarin de:
	//Rode, gele, blauwe en grijze auto's kan zien en wat dit betekend.
	//Ook kan je zien waar rood, oranje en groen voor staat.(Vol, bijna vol en vrij).
	public void updateView() {
		int cars = 540 - model.getNumberOfOpenSpots();
		String jLabel = "<html>";
		jLabel +=  "<tr><th ALIGN = LEFT><font size='5'>Legenda:</font></th></tr>";
		jLabel +=  "<tr><th ALIGN = LEFT><font color='red'>Rood = Gewone auto</font></tr></th>";
		jLabel +=  "<tr><th ALIGN = LEFT><font color='#66ccff'>Lichtblauw = Gereserverde plek</font></th></tr>";
		jLabel +=  "<tr><th ALIGN = LEFT><font color='blue'>Blauw = Gereserverde auto</font></th></tr>";
		jLabel +=  "<tr><th ALIGN = LEFT><font color='green'>Groen = Pashouder auto</font></th></tr>";
		jLabel +=  "<tr><th ALIGN = LEFT>Wit = Lege plek</th></tr>";
		jLabel +=  "<font size='5'>Status:</font><Br>";
		if (cars > 500) {
			jLabel += "<font size='7' color='red'>VOL</font>";
		} else if (cars > 432) {
			jLabel += "<font size='7' color='orange'>BIJNA VOL</font>";
		} else {
			jLabel += "<font size='7' color='green'>VRIJ</font>";
		}
		jLabel += "<html>";
		labels.setText(jLabel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// als je background color wit wilt, remove super.paint en comment g.setcolor en fillrect terug in.
		//g.setColor(Color.white);
		//g.fillRect(0, 0, 300, 342); 
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 300, 342);
    }
	
}


	


			

			
		