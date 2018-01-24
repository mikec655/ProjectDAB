package logic;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private double money =0;
    
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
    public double getmissedprofit() {
    	money = 0;
   	   	for(Car carnaam: queue) {
        money= money + carnaam.getPayment();     
}

    	return money;
    }
}
