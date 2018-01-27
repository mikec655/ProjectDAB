package logic;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private Color color;
    
	public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(false);
        payment = 0;
        color = Color.green;
    }
    
    public Color getColor(){
    	return color;
    }
    
    public void setColor(){
    	color = new Color(0,0,255);
    }
    
}
