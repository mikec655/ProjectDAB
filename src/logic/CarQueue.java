package logic;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private double money =0;
    private int numberofrescars = 0;
    private int numberofadhoccars = 0;
    private int numberofpasscars = 0;
    
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
    public int getqueueResCar() {
    	return  numberofrescars;
    }
    //kan voor elke entrancequeue gebruikt worden.
    public int getqueueAdHocCar() {
    	return  numberofadhoccars;
    }
    //kan voor elke entrancequeue gebruikt worden.
    public int getqueuePassCar() {
    	return  numberofpasscars;
    }
    
    public double getmissedprofit() {
     money = 0;
     numberofrescars = 0;
     numberofadhoccars = 0;
     numberofpasscars = 0;
   	 try {  	
	     for(Car carnaam: queue) {
	        	if(carnaam instanceof ResCar) {
	        		numberofrescars +=1;
	        	}
	        	else if(carnaam instanceof ParkingPassCar){
	        		 numberofpasscars +=1;
	        	}
	        	else if(carnaam instanceof AdHocCar){
	        		numberofadhoccars +=1;
	        	}
	   	  }
	   
    } catch (Exception ex) {
    	
    } 
   	return money;
    }
}
