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
        String text = "";
        double placepercent = getNumberOfPlaces();
        text += "<html>Het percentage open plaatsen is: " ;
        text += formatter.format(placepercent) + "% <br /> ProfitAvgPerUur: ";
        text += formatter.format(model.getProfitAv()) + "<br />"+ "Profit totaal: " + formatter.format(model.getProfit());
        text += "<br>Profit avg per dag: " + formatter.format(model.getProfitAv() * 24);
        text += "<br>Profit avg per week: " + formatter.format(model.getProfitAv() * 24 * 7);
        text += "<br>Profit avg per maand: " + formatter.format(model.getProfitAv() * 24 * 365 / 12);
        text += "<br>Profit avg per jaar: " + formatter.format(model.getProfitAv() * 24 * 365)+"</html>";
        text += "<br>Profit avg per adhoc " + formatter.format(model.getProfitAv() * 24 * 365)+"</html>";
        show.setText(text);
       
    }
   
    private double getNumberOfPlaces() {
    	double tempspots = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
    	double placepercent = 1.0 * model.getNumberOfOpenSpots() / tempspots * 100.00;
        return placepercent;         
    }
   
 
       
}