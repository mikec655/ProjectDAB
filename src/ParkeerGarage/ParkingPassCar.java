package ParkeerGarage;
import java.util.Random;
import java.awt.*;
/**
 * 
 * @author ProjectGroup
 * @version 1.0
 *
 */
public class ParkingPassCar extends Car {
	//Instance Fields
	private static final Color COLOR=Color.blue;
	//Constructor
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    /**
     * @return Color
     */
    public Color getColor(){
    	return COLOR;
    }
}
