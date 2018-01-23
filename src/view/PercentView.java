package view;
 
import javax.swing.JLabel;
 
import logic.AdHocCar;
import logic.Model;
import java.awt.FlowLayout;
import java.awt.GridLayout;
 
public class PercentView extends AbstractView{
    private static final long serialVersionUID = 1337;
    private JLabel show;
   
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
        placepercent = getNumberOfPlaces(placepercent);
        show.setText("<html>Het percentage open plaatsen is: " + String.valueOf(placepercent) + "% <br /> Profit: " + model.getprofitAv() + "</html>");
       
    }
   
    private double getNumberOfPlaces(double placepercent) {
 
                double tempspots = 1.0 * (model.getNumberOfPlaces() * model.getNumberOfFloors() * model.getNumberOfRows());
                placepercent = 1.0 * model.getNumberOfOpenSpots() / tempspots * 100.00;
                placepercent = Math.round(placepercent * 10.00);
                placepercent = placepercent / 10.00;
                return placepercent;
               
        }
   
 
       
}