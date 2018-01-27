package logic;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private Color color;
	public static double pricePerHour = 2;
    
    public AdHocCar() {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(true);
        payment = (1 + stayMinutes / 60)  * pricePerHour;
        color = Color.RED;
    }
    
    public static void setPricePerHour(double price) {
    	pricePerHour = price;
    }
    
    public Color getColor(){
    	return color;
    }
   
}

