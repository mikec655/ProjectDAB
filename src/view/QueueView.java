package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import logic.Model;

//QueueView extends AbstractView 
public class QueueView extends AbstractView {
	private static final long serialVersionUID = -3842302968396266777L;
	private JLabel show;
	JProgressBar progressBar;
	JProgressBar progressBarpas;
	
	
	//Hier wordt de super aangeroepen van de klasse AbstractView.
	public QueueView(Model model) {
		super(model);
		 show = new JLabel();
	        show.setVerticalTextPosition(JLabel.TOP);
	        show.setHorizontalTextPosition(JLabel.LEFT);
	        
	        progressBar = new JProgressBar(0, 20);
	        progressBar.setValue(0);
	        progressBar.setStringPainted(true);
	        progressBar.setString("0");
	        
	        progressBarpas = new JProgressBar(0, 20);
	        progressBarpas.setValue(0);
	        progressBarpas.setStringPainted(true);
	        progressBarpas.setString("0");
	        setUpPanel();
	}
	 
	//Hier kan je de panel van de FlowLayout mee zien, wordt ook twee keer een progressbar toegevoegd.
	 private void setUpPanel(){
	        setLayout(new FlowLayout());
	        add(show);
	        add(progressBar);
	        add(progressBarpas);
	    }
	 
	 //Update de view waarbij je een legenda kan zien waarin je de:
	 //Auto's die in de queue staan voor de normale ingang en auto's die in de queue staan voor abonnement plekken.
	 public void updateView() {
		 	
		 	progressBar.setValue(model.getentranceCarQueuesize());
		 	progressBarpas.setValue(model.getentrancePassQueuesize());
	        String text = "";
	        text += "<html>";
	        text += "<tr><td>Auto's in Entrance queue:</td>"+ "<td>" + model.getentranceCarQueuesize()+ "</td></tr>";
	        text += "<tr><td>Auto's in Pas queue:</td>"+ "<td>" + model.getentrancePassQueuesize()+ "</td></tr>";
	        show.setText(text);
	        progressBar.setString(""+model.getentranceCarQueuesize()+"");
	        progressBar.setString(""+model.getentrancePassQueuesize()+"");
	       
	    }

				
}
