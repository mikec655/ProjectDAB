package logic;

import java.util.Random;
import java.awt.*;

public class ResCar extends Car {
	private  Color COLOR;
	
	public ResCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        payment = (double)(stayMinutes * 0.02) + 3;
        COLOR=Color.yellow;
    }
    
    public Color getColor(){
    	return COLOR;
    }
    public Color setColor(){
    	COLOR = Color.LIGHT_GRAY;
    	return null;
    }
    
}

