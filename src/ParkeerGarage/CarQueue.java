package ParkeerGarage;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author ProjectGroup 
 * @version 1.0
 *
 */

public class CarQueue {
	//instance fields
    private Queue<Car> queue = new LinkedList<>();
    /**
     * 
     * @param car
     * @return queue.add(car)
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }
    /**
     * 
     * @return queue.poll()
     */
    public Car removeCar() {
        return queue.poll();
    }
    /**
     * 
     * @return queue.size()
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
