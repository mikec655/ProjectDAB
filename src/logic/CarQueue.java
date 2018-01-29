package logic;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private int numberOfResCars;
    private int numberOfAdHocCars;
    private int numberOfPassCars;
    
    public CarQueue() {
    	numberOfResCars = 0;
        numberOfAdHocCars = 0;
        numberOfPassCars = 0;
    }
    
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return queue.size();
    }
    
    public Car peekCar() {
    	return queue.peek();
    }
    
    //kan voor elke entrancequeue gebruikt worden.
    public int getNumberOfResCarsInQueue() {
    	return numberOfResCars;
    }
    //kan voor elke entrancequeue gebruikt worden.
    public int getNumberOfAdHocCarsInQueue() {
    	return numberOfAdHocCars;
    }
    //kan voor elke entrancequeue gebruikt worden.
    public int getNumberOfPassCarsInQueue() {
    	return numberOfPassCars;
    }
    
    public void updateNumbers() {
    	numberOfResCars = 0;
        numberOfAdHocCars = 0;
        numberOfPassCars = 0;
        
    	for(Car carnaam: queue) {
        	if(carnaam instanceof ResCar) {
        		numberOfResCars++;
        	}
        	else if(carnaam instanceof ParkingPassCar){
        		 numberOfPassCars++;
        	}
        	else if(carnaam instanceof AdHocCar){
        		numberOfAdHocCars++;
        	}
   	  	}
    }
    
}
