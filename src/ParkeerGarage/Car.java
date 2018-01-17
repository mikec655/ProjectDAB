package ParkeerGarage;
/*
 * miketestboyo v2
* berry
 *  
 */

import java.awt.*;

public abstract class Car {
// instance Fields
    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }
    /**
     * 
     * @return location
     */
    public Location getLocation() {
        return location;
    }
    /**
     * 
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    /**
     * 
     * @return minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }
    /**
     * 
     * @param minutesLeft
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    /**
     * 
     * @return isPlaying
     */
    public boolean getIsPaying() {
        return isPaying;
    }
    /**
     * 
     * @param isPaying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }
    /**
     * 
     * @return hasToPay
     */
    public boolean getHasToPay() {
        return hasToPay;
    }
    /**
     * 
     * @param hasToPay
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }
    /**
     * minutesleft --
     */
    public void tick() {
        minutesLeft--;
    }
    /**
     * 
     * @return 	color Abstractclass
     */
    public abstract Color getColor();
}