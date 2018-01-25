package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import logic.Model;

//NumberView extends AbstractView
public class NumberView extends AbstractView{
	private static final long serialVersionUID = 1337;
    private JLabel show;
   
   //Hier wordt de super aangeroepen van de klasse AbstractView.
    public NumberView(Model model) {
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
   
    //Update de view met daarbij het aantal plaatsen:
    //Aantal lege plekken, aantal auto's, abonnementhouders, gereserveerde auto's.
    //Misgelopen auto's waarbij je kan zien welke auto het is.
    public void updateView() {
        String text = "";
        int total = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
        int totalautos = model.getAmountOfResCars() + model.getAmountOfPassCars() + model.getAmountOfAdHocCars();
        text += "<html>";
        text += "<tr><td>Totaal Aantal plaatsen:</td>"+ "<td>" + total + "</td></tr>";
        text += "<tr><td>Totaal Aantal lege plekken:</td>" + "<td>" + model.getNumberOfOpenSpots() + "</td></tr>";
        text += "<tr><td>Totaal Aantal auto's:</td>" + "<td>" + totalautos + "</td></tr>";
        text += "<tr><td>Aantal Normale auto's:</td>" + "<td>" + model.getAmountOfAdHocCars() + "</td></tr>";
        text += "<tr><td>Aantal Abonnementhouders:</td>" + "<td>" + model.getAmountOfPassCars() + "</td></tr>";
        text += "<tr><td>Aantal Gereserveerde auto's:</td>" + "<td>" + model.getAmountOfResCars() + "</td></tr>";
        text += "<tr><td>Aantal Misgelopen auto's:</td>" + "<td>" + model.getNumberofLeaving() + "</td></tr>";
        text += "<P ALIGN=LEFT><tr><td> - Waarvan AdhocCar:</td></p>" + "<td>" + model.getamountofAdHocCarleft() + "</td></tr>";
        text += "<P ALIGN=LEFT><tr><td>- Waarvan ResCar:</td>" + "<td>" + model.getamountofResCarleft() + "</td></tr>";
        text += "<P ALIGN=LEFT><tr><td> - Waarvan PassCar:</td>" + "<td>" + model.getamountofPassCarleft() + "</td></tr>";
        show.setText(text);
       
    }
}
