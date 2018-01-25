package view;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import logic.Model;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.*;

public class Labels extends AbstractView{
	private static final long serialVersionUID = 1;
	private Border blackline;
	private JLabel labels;
	
	public Labels(Model model) {
		super(model);
		
		blackline = BorderFactory.createLineBorder(Color.black, 1 );
		labels = new JLabel();
		labels.setVerticalTextPosition(JLabel.BOTTOM);
		labels.setHorizontalTextPosition(JLabel.CENTER);
		labels.setOpaque(true);
		labels.setBackground(Color.WHITE);
		labels.setBorder(new CompoundBorder((blackline),BorderFactory.createEmptyBorder(20,20,20,20)));
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		add(labels);
	}
	
	public void updateView() {
		String jLabel = "<html>";
		jLabel +=  " <font size='5'>Legenda:</font><br>";
		jLabel +=  " <font color='red'>Rood = Gewone auto</font><br>";
		jLabel +=  " <font color='yellow'>Geel = Gereserverde auto</font><br>";
		jLabel +=  " <font color='blue'>Blauw = Pashouder auto</font><br>";
		jLabel +=  " <font color='gray'>Grijs = Gereserverde plek</font><br>";
		jLabel +=  " Wit = Lege plek<html>";
		labels.setText(jLabel);
	}
	
	
	
}


	


			

			
		