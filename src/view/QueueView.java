package view;

import java.awt.Dimension;

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
	
	public Dimension getPreferredSize() {
        return new Dimension(300, 20);
    }
	
	//Hier wordt de super aangeroepen van de klasse AbstractView.
	public QueueView(Model model) {
		super(model);
		 show = new JLabel();
	        show.setVerticalTextPosition(JLabel.TOP);
	        show.setHorizontalTextPosition(JLabel.LEFT);
	        
		 show1 = new JLabel();
	        show1.setVerticalTextPosition(JLabel.TOP);
	        show1.setHorizontalTextPosition(JLabel.LEFT);
	        
	        progressBar = new JProgressBar(0, model.getQueueSize());
	        progressBar.setValue(0);
	        progressBar.setPreferredSize(getPreferredSize());
	        progressBar.setStringPainted(true);
	        progressBar.setString(" 0");
	        
	        
	        progressBarpas = new JProgressBar(0, model.getQueueSize());
	        progressBarpas.setValue(0);
	        progressBarpas.setStringPainted(true);
	        progressBarpas.setString(" 0");
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
		 	progressBar.setMaximum(model.getQueueSize());
		 	progressBarpas.setMaximum(model.getQueueSize());
		 	
	        String text = "";
	        text += "<html>";
	        text += "<table BORDER=0.5 CELLSPACING=0 CELLPADDING=5><tr><th>Auto's in Regulierewachtrij:</th><td >" + model.getentranceCarQueuesize()+ " Totaal.</td></tr>" ;
	        text += "<tr><th rowspan=2 ALIGN=RIGHT VALIGN=TOP>waarvan:</th><td WIDTH=120>"+ model.getamountofAdHocCarinEntrancequeue() +" Normaalauto's.</td></tr>";
	        text +=	"<tr><td  WIDTH=120>"+ model.getamountofResCarinEntrancequeue() + " Reserveerauto's.</td></tr></table>";

	        show.setText(text);
	        
	        String text1 = "";
	        text1 += "<html><br>";
	        text1 += "<table BORDER=0.5 CELLSPACING=0 CELLPADDING=5  ><tr><th WIDTH=166>Auto's in Abonnementenwachtrij:</th><td WIDTH=100  VALIGN=BOTTOM>" + model.getentrancePassQueuesize()+ " AboAuto's.</td></tr>" ;
	        show1.setText(text1);
	        
	        progressBar.setValue(model.getentranceCarQueuesize());
		 	progressBarpas.setValue(model.getentrancePassQueuesize());
	        progressBar.setString(""+model.getentranceCarQueuesize()+"");
	        progressBarpas.setString(""+model.getentrancePassQueuesize()+"");
	       
	    }

				
}
