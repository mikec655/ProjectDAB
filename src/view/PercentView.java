package view;
 
import javax.swing.JLabel;
import logic.Model;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
 
public class PercentView extends AbstractView{
    private static final long serialVersionUID = 1337;
    private JLabel show;
    private NumberFormat formatter = new DecimalFormat("#0.00");
   
    public PercentView(Model model) {
        super(model);
        show = new JLabel();
        show.setVerticalTextPosition(JLabel.TOP);
        show.setHorizontalTextPosition(JLabel.RIGHT);
        setUpPanel();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }
   
    private void setUpPanel(){
        setLayout(new FlowLayout());
        add(show);
    }
   
    public void updateView() {
    	// text += "<html>";
        //text += "<tr><td>Totaal aantal plaatsen:</td>"+ "<td>" + total + "</td></tr>";
        String text = "<html>";
        double placepercent = getNumberOfPlaces();
        text += "<tr><td>Het percentage open plaatsen is:</td>" + "<td>" + formatter.format(placepercent) + "%</td></tr>";
        text += "<tr><td>ProfitAvgPerUur: </td>"  + "<td>" + formatter.format(model.getProfitAv()) + "</td></tr>";
        text += "<tr><td>Profit totaal: </td>" +  "<td>" + formatter.format(model.getProfit()) + "</td></tr>";
        text += "<tr><td>Profit avg per dag: </td>" + "<td>" + formatter.format(model.getProfitAv() * 24)+ "</td></tr>";
        text += "<tr><td>Profit avg per week: </td>" + "<td>" + formatter.format(model.getProfitAv() * 24 * 7)+ "</td></tr>";
        text += "<tr><td>Profit avg per maand:</td> " + "<td>" + formatter.format(model.getProfitAv() * 24 * 365 / 12)+ "</td></tr>";
        text += "<tr><td>Profit avg per jaar:</td> " + "<td>" + formatter.format(model.getProfitAv() * 24 * 365)+ "</td></tr>";
        text += "<tr><td>Profit totaal normale/rode auto's: </td>" + "<td>" + formatter.format(model.getProfitADH())+ "</td></tr>";
        text += "<tr><td>Profit totaal reserverende/gele auto's: </td>" + "<td>" + formatter.format(model.getProfitres())+ "</td></tr>";
        text += "<tr><td>Profit misgelopen: </td>"+ "<td>" + formatter.format(model.getMissedProfit())+"</td></tr></html>";
        show.setText(text);
       
    }
   
    private double getNumberOfPlaces() {
    	double tempspots = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
    	double placepercent = 1.0 * model.getNumberOfOpenSpots() / tempspots * 100.00;
        return placepercent;         
    }
   
 
       
}