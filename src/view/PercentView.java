package view;
 
import javax.swing.JLabel;
 
import logic.AdHocCar;
import logic.Model;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
   
    private void setUpPanel(){
        setLayout(new GridLayout(0, 1));
        add(show);
    }
   
    public void updateView() {
       
       
        double placepercent=0;
       
       
        String text = "";
        placepercent = getNumberOfPlaces(placepercent);
        text += "<html>Het percentage open plaatsen is: " ;
        text += formatter.format(placepercent) + "% <br /> ProfitAvg: ";
        text += formatter.format(model.getProfitAv()) + "<br />"+ "Profit: " + formatter.format(model.getProfit())+"</html>";
        show.setText(text);
       
    }
   
    private double getNumberOfPlaces(double placepercent) {
 
                double tempspots = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
                placepercent = 1.0 * model.getNumberOfOpenSpots() / tempspots * 100.00;
                return placepercent;
               
        }
   
 
       
}