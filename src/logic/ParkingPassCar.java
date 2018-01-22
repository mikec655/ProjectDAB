package logic;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;
	public double paymentPass;
    
	public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
       double paymentPass = 20.00;
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
    public double getpaymentPass() {
    	return paymentPass;
    }
}
