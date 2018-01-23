package logic;

import java.util.Random;
import java.awt.*;

public class ResCar extends Car {
	private static final Color COLOR=Color.yellow;
	
	public ResCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        payment = (stayMinutes * 0.02) + 3;
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
}

