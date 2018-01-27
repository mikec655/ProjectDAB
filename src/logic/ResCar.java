package logic;

import java.util.Random;
import java.awt.*;

public class ResCar extends Car {
	private Color color;
	public static double pricePerHour = 2;
	public static double priceReservation = 3;
    
	public ResCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(true);
        payment = (1 + stayMinutes / 60)  * pricePerHour + priceReservation;
        color = new Color(102, 204, 255);
    }
	
	public static void setPricePerHour(double price) {
    	pricePerHour = price;
    }
	
	public static void setPriceReservation(double price) {
    	priceReservation = price;
    }
    
    public Color getColor(){
    	return color;
    }
    
    private void setColor(){
    	color = Color.BLUE;
    }
    
    public void tick() {
    	if(getStayMinute()-15 == getMinutesLeft()) {
    		setColor();
    	}
    	super.tick();
    }

	@Override
	public String toString() {
		return "3";
	}
}

