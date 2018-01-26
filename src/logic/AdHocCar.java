package logic;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private Color color;
    
    public AdHocCar() {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(true);
        paymentADH = (double) stayMinutes * carprice;
        color = Color.RED;
    }
    
    public Color getColor(){
    	return color;
    }
    
    public void setColor(){
    	color = Color.YELLOW;
    }
   
}

