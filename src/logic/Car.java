package logic;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    protected int stayMinutes;
    protected double payment;
    protected double paymentADH;
    protected double paymentres;
    public static double carprice;


    
    
    //auto's
    public Car() {

    }
    
    public static void SetCarPrice(double setcarprice) {
    	carprice = setcarprice;
    }

    //Haal de locatie op van de auto
    public Location getLocation() {
        return location;
    }
    
    //Set de locatie in de nieuwe locatie
    public void setLocation(Location location) {
        this.location = location;
    }
    
    //Haal de minuten op die er nog over zijn dat de auto er geparkeerd staat??
    public int getMinutesLeft() {
        return minutesLeft;
    }
    
    //Zet de minuten die nog over zijn in minutesLeft
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    //Is paying is een boolean zodat het of true of false is dit is een state. 
    public boolean getIsPaying() {
        return isPaying;
    }
    
    // IsPaying boolean true of false dus kun je zeggen dat een klant aan het betalen is.	
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }
    
    // Heeft te betalen word gereturned
    public boolean getHasToPay() {
        return hasToPay;
    }
    
    // Hier kun je neerzetten wat je nog moet betalen. 
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }
    
    // Blijft nog aantal minuten word uitgeprint
    public int getStayMinute() {
    	return stayMinutes;
    
    }
    
    public void setStayMinute(int stayMinutes1) {
    	this.stayMinutes = stayMinutes1;
    }
    
 // Nieuwe getpayment
    public double getPayment() {
    	return payment = paymentres + paymentADH;
    }
    
    // Nieuwe getpaymentADH
    public double getPaymentADH() {
    	return paymentADH;
    }
    
    public double getPaymentres() {
    	return paymentres;
    }
    

    public void tick() {
    	if(this instanceof ResCar) {
    		if(getStayMinute()-15 == getMinutesLeft()) {
    	    	setColor();
    		}
    	}
    	minutesLeft--;
    }
    
    public abstract Color getColor();
    public abstract void setColor();
   	 
}
