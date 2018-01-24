package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import logic.Model;

public class NumberView extends AbstractView{
	private static final long serialVersionUID = 1337;
    private JLabel show;
   
    public NumberView(Model model) {
        super(model);
        show = new JLabel();
        show.setVerticalTextPosition(JLabel.TOP);
        show.setHorizontalTextPosition(JLabel.LEFT);
        setUpPanel();
    }
   
    private void setUpPanel(){
        setLayout(new FlowLayout());
        add(show);
    }
   
    public void updateView() {
        String text = "";
        int total = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
        int totalautos = model.getAmountOfResCars() + model.getAmountOfPassCars() + model.getAmountOfAdHocCars();
        text += "<html>";
        text += "<tr><td>Totaal aantal plaatsen:</td>"+ "<td>" + total + "</td></tr>";
        text += "<tr><td>Totaal aantal lege plekken:</td>" + "<td>" + model.getNumberOfOpenSpots() + "</td></tr>";
        text += "<tr><td>Totaal aantal auto's:</td>" + "<td>" + totalautos + "</td></tr>";
        text += "<tr><td>Aantal normale auto's:</td>" + "<td>" + model.getAmountOfAdHocCars() + "</td></tr>";
        text += "<tr><td>Aantal abbonnementhouders:</td>" + "<td>" + model.getAmountOfPassCars() + "</td></tr>";
        text += "<tr><td>Aantal gereserveerde auto's:</td>" + "<td>" + model.getAmountOfResCars() + "</td></tr>";
        text += "<tr><td>Aantal misgelopen auto's:</td>" + "<td>" + model.getNumberofLeaving() + "</td></tr>";
        text += "<tr><td>gemiste geld:</td>" + "<td>" + model.getMissedProfit() + "</td></tr>";
        show.setText(text);
       
    }
}
