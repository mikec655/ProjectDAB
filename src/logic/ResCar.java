package logic;

import java.util.Random;
import java.awt.*;

public class ResCar extends Car {
	private  Color color;
    
	public ResCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setStayMinute(stayMinutes);
        this.setHasToPay(true);
        paymentres = (double)(stayMinutes * carprice) + 3;
        color = new Color(0,255,255);
    }
    
    public Color getColor(){
    	return color;
    }
    public void setColor(){
    	color = new Color(0,0,255);
    }
    
}

