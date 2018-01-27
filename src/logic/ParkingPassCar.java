package logic;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private Color color;
	private static double pricePerMonth; 
    
	public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(false);
        pricePerMonth = 30.0;
        payment = 0;
        color = Color.GREEN;
    }
    
    public Color getColor(){
    	return color;
    }

	@Override
	public String toString() {
		return "2";
	}
	
	public static double getPricePerMonth(){
    	return pricePerMonth;
    }
	
	public static void setPricePerMonth(double price) {
		pricePerMonth = price;
	}
    
}
