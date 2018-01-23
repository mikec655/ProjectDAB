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
        text += formatter.format(placepercent) + "% <br /> ProfitAvg: ";
        text += formatter.format(model.getProfitAv()) + "<br />"+ "Profit: " + formatter.format(model.getProfit())+"</html>";
        show.setText(text);
       
    }
   
    private double getNumberOfPlaces() {
    	double tempspots = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
    	double placepercent = 1.0 * model.getNumberOfOpenSpots() / tempspots * 100.00;
        return placepercent;         
    }
   
 
       
}