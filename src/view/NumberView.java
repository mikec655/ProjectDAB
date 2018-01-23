package view;

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
        show.setHorizontalTextPosition(JLabel.RIGHT);
        setUpPanel();
    }
   
    private void setUpPanel(){
        setLayout(new GridLayout(0, 1));
        add(show);
    }
   
    public void updateView() {
        String text = "";
        int total = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
        text += "<html> ";
        text += "Totaal aantal auto's: " + total + "<br>";
        text += "Aantal normale auto's: " + model.getAmountOfAdHocCars() + "<br>";
        text += "Aantal abbonnementhouder auto's: " + model.getAmountOfPassCars() + "<br>";
        text += "Aantal gereserveerde auto's: " + model.getAmountOfResCars() + "<br>";
        text += "Aantal lege plekken: " + model.getNumberOfOpenSpots() + "</html>";
        show.setText(text);
       
    }
}
