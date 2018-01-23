package logic;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private Color COLOR;
    
	public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(false);
        payment = 0;
        COLOR=Color.blue;
    }
    
    public Color getColor(){
    	return COLOR;
    }
    public Color setColor(){
    	COLOR = Color.YELLOW;
    	return null;
    }
    
}
