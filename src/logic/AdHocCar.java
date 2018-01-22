package logic;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
	public double paymentADH;
	public int stayMinutes;
    public AdHocCar() {
    	Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        double paymentADH = (stayMinutes * 0.02);
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
    public double getPaymentADH() {
    	return paymentADH;
    }
}

