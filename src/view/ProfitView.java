package view;
 
import javax.swing.JLabel;
import logic.Model;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
 
//PercentView extends AbstractView.
public class ProfitView extends AbstractView{
    private static final long serialVersionUID = 1337;
    private JLabel show;
    private NumberFormat formatter = new DecimalFormat("#0.00");
   
    //Hier wordt de super aangeroepen van de klasse AbstractView.
    public ProfitView(Model model) {
        super(model);
        show = new JLabel();
        show.setVerticalTextPosition(JLabel.TOP);
        show.setHorizontalTextPosition(JLabel.RIGHT);
        setUpPanel();
    }
    
    //Hier wordt de dimension van de car gezet.
    public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }
   
    //Hier kan je de panel van de FlowLayout mee zien.
    private void setUpPanel(){
        setLayout(new FlowLayout());
        add(show);
    }
   
    //Update de view waarbij je een legenda kan zien met daar in de percentage van op plaatsen:
  	//Profit average per uur, totaal profit, average per dag/week/maand/jaar.
    //Totaal van de normale/rode auto's en de gereserveerde/gele auto's.
  	//Ook kan je zien hoeveel profit er is misgelopen.
    public void updateView() {
        String text = "<html>";
        text += "<tr><td>Totale omzet:</td>" +  "<td>&euro;" + formatter.format(model.getProfit()) + "</td></tr>";
        text += "<tr><td>Totale omzet rode auto's:</td>" + "<td>&euro;" + formatter.format(model.getProfitAdHoc())+ "</td></tr>";
        text += "<tr><td>Totale omzet blauwe auto's:</td>" + "<td>&euro;" + formatter.format(model.getProfitRes())+ "</td></tr>";
        text += "<tr><td>Totale omzet groene auto's:</td>" + "<td>&euro;" + formatter.format(model.getProfitPass())+ "</td></tr>";
        text += "<tr><td>Misgelopen omzet:</td>"+ "<td>&euro;" + formatter.format(model.getMissedProfit())+"</td></tr></html>";
        text += "<tr><td>Gem. omzet/uur:</td>"  + "<td>&euro;" + formatter.format(model.getProfitAverage()) + "</td></tr>";
        text += "<tr><td>Gem. omzet/dag:</td>" + "<td>&euro;" + formatter.format(model.getProfitAverage() * 24)+ "</td></tr>";
        text += "<tr><td>Gem. omzet/week:</td>" + "<td>&euro;" + formatter.format(model.getProfitAverage() * 24 * 7)+ "</td></tr>";
        text += "<tr><td>Gem. omzet/maand:</td> " + "<td>&euro;" + formatter.format(model.getProfitAverage() * 24 * 365 / 12)+ "</td></tr>";
        text += "<tr><td>Gem. omzet/jaar:</td> " + "<td>&euro;" + formatter.format(model.getProfitAverage() * 24 * 365)+ "</td></tr>";
       
        show.setText(text);
       
    }
   
    //Dit vraagt op of er nog plaatsen over zijn in de CarPark.
    private double getNumberOfPlaces() {
    	double tempspots = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
    	double placepercent = 1.0 * model.getNumberOfOpenSpots() / tempspots * 100.00;
        return placepercent;         
    }
   
 
       
}