package view;

import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import logic.Model;

//QueueView extends AbstractView 
public class QueueView extends AbstractView {
	private static final long serialVersionUID = -3842302968396266777L;
	private JLabel show;
	private JLabel show1;

	private JProgressBar progressBar;
	private JProgressBar progressBarpas;
	
	
	//Hier wordt de super aangeroepen van de klasse AbstractView.
	public QueueView(Model model) {
		super(model);
		 show = new JLabel();
	        show.setVerticalTextPosition(JLabel.TOP);
	        show.setHorizontalTextPosition(JLabel.LEFT);
	        
		 show1 = new JLabel();
	        show1.setVerticalTextPosition(JLabel.TOP);
	        show1.setHorizontalTextPosition(JLabel.LEFT);
	        
	        progressBar = new JProgressBar(0, 20);
	        progressBar.setValue(0);
	        progressBar.setSize(10,2);
	        progressBar.setStringPainted(true);
	        progressBar.setString("0");
	        
	        
	        progressBarpas = new JProgressBar(0, 21);
	        progressBarpas.setValue(0);
	        progressBarpas.setStringPainted(true);
	        progressBarpas.setString("0");
	        setUpPanel();
	}
	 
	//Hier kan je de panel van de FlowLayout mee zien, wordt ook twee keer een progressbar toegevoegd.
	 private void setUpPanel(){

		 	BoxLayout box = new BoxLayout (this, BoxLayout.Y_AXIS);
		    add(show);
		    add(progressBar);
	        add(show1);
	        add(progressBarpas);
	        setLayout (box);
	    }
	 
	 //Update de view waarbij je een legenda kan zien waarin je de:
	 //Auto's die in de queue staan voor de normale ingang en auto's die in de queue staan voor abonnement plekken.
	 public void updateView() {
		 	
		 	
	        String text = "";
	        text += "<html>";
	        text += "<table BORDER=0.5 CELLSPACING=0 CELLPADDING=5><tr><th>Auto's in Entrance queue:</th><td>" + model.getentranceCarQueuesize()+ " Totaal.</td></tr>" ;
	        text += "<tr><th rowspan=2 ALIGN=RIGHT VALIGN=TOP>waarvan:</th><td>"+ model.getamountofAdHocCarinEntrancequeue() +" AdHoccars.</td></tr>";
	        text +=	"<tr><td>"+ model.getamountofResCarinEntrancequeue() + " Rescars.</td></tr></table>";

	        show.setText(text);
	        
	        String text1 = "";
	        text1 += "<html>";
	        text1 += "<tr><td>Auto's in Pas queue:</td>"+ "<td>" + model.getentrancePassQueuesize()+ "</td></tr>";
	        show1.setText(text1);
	        
	        progressBar.setValue(model.getentranceCarQueuesize());
		 	progressBarpas.setValue(model.getentrancePassQueuesize());
	        progressBar.setString(""+model.getentranceCarQueuesize()+"");
	        progressBarpas.setString(""+model.getentrancePassQueuesize()+"");
	       
	    }

				
}
