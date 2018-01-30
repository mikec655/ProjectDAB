package view;

import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;

import logic.Model;
import logic.ParkingPassCar;
import logic.ResCar;

public class PricesView extends AbstractView {
	private static final long serialVersionUID = -6849566605068085259L;
	private JLabel show;
	private NumberFormat formatter = new DecimalFormat("#0.00");

	public PricesView(Model model) {
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
        text += "<tr><td>Prijs per uur:</td>"+ "<td>&euro;" + formatter.format(ResCar.pricePerHour) + "</td></tr>";
        text += "<tr><td>Prijs voor reserveren:</td>" + "<td>&euro;" + formatter.format(ResCar.priceReservation) + "</td></tr>";
        text += "<tr><td>Abonnement prijs per maand:</td>" + "<td>&euro;" + formatter.format(ParkingPassCar.getPricePerMonth()) + "</td></tr>";
        show.setText(text);
       
    }

}
