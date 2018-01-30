package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import logic.Model;

public class QueueSettingsView extends AbstractView {
	private static final long serialVersionUID = 665843712835665725L;
	private JLabel show;

	public QueueSettingsView(Model model) {
		super(model);
		show = new JLabel();
        show.setVerticalTextPosition(JLabel.TOP);
        show.setHorizontalTextPosition(JLabel.LEFT);
        setUpPanel();
	}
    
    //Hier kan je de panel van de FlowLayout mee zien.
    private void setUpPanel(){
        setLayout(new FlowLayout());
        add(show);
    }
   
    public void updateView() {
        String text = "";
        text += "<html>";
        text += "<tr><td>Grootte van de rijen:</td>"+ "<td>" + model.getQueueSize() + "</td></tr>";
        text += "<tr><td>Binnenrij snelheid</td>" + "<td>" + model.getenterSpeed() + "</td></tr>";
        text += "<tr><td>Betaal snelheid</td>" + "<td>" + model.getpaymentSpeed() + "</td></tr>";
        text += "<tr><td>Uitrij snelheid</td>" + "<td>" + model.getexitSpeed() + "</td></tr>";
        show.setText(text);
       
    }

}
