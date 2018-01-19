package logic;

import java.util.Random;

//implements runnable zorgt ervoor dat er threading komt. Dus de functies toevoegen aan de infiniteloops.
public class Model extends AbstractModel implements Runnable{
	
	private boolean run;
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    private Car[][][] cars;
    

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;
    
    int numberOfFloors;
    int numberOfRows;
    int numberOfPlaces;
    int numberOfOpenSpots;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
	
	public Model(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		run = false;
		entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
	}
	
	//per tick gaat de tijd omhoog en handelt de wachtrijen af en update de views.
	//sleep zorgt ervoor dat het programma gaat "slapen".
	public void run() {
		run = true;
		while(run) {
			tick();
			try {
				Thread.sleep(tickPause);
			} catch (Exception e) {} 
		}
		
	}
	
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		run = false;
	}
	
	private void tick() {
    	advanceTime();
    	handleExit();
    	carTick();
    	notifyViews();
    	handleEntrance();
    }
	
	public int getTickPause() {
    	return tickPause;
    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
	
	//Time methods
	private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }
	
	//Getters of places
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    //Setters of places
    public void setNumberOfFloors(int numberofFloors) {
        this.numberOfFloors = numberofFloors;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setNumberOfOpenSpots(int numberOfOpenSpots){
    	this.numberOfOpenSpots = numberOfOpenSpots;
    }
    
    //Getters of arrivals 
    public void getweekDayArrivals(int weekDayArrivals) {
        this.weekDayArrivals = weekDayArrivals;
    }
    
    public void getweekendArrivals(int weekendArrivals) {
        this.weekendArrivals = weekendArrivals;
    }
    
    public void getweekDayPassArrivals(int weekDayPassArrivals) {
        this.weekDayPassArrivals = weekDayPassArrivals;
    }
    
    public void getweekendPassArrivals(int weekendPassArrivals) {
        this.weekendPassArrivals = weekendPassArrivals;
    }
    
    
    //Setters of arrivals
    public void setweekDayArrivals(int weekDayArrivals) {
        this.weekDayArrivals = weekDayArrivals;
    }
    
    public void setweekendArrivals(int weekendArrivals) {
        this.weekendArrivals = weekendArrivals;
    }
    
    public void setweekDayPassArrivals(int weekDayPassArrivals) {
        this.weekDayPassArrivals = weekDayPassArrivals;
    }
    
    public void setweekendPassArrivals(int weekendPassArrivals) {
        this.weekendPassArrivals = weekendPassArrivals;
    }
    
    
    //Getters of speeds
    public void getenterSpeed(int enterSpeed) {
        this.enterSpeed = enterSpeed;
    }
    
    public void getpaymentSpeed(int paymentSpeed) {
        this.paymentSpeed = paymentSpeed;
    }
    
    public void getexitSpeed(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }
    
    
    //Setters of speeds
    public void setenterSpeed(int enterSpeed) {
        this.enterSpeed = enterSpeed;
    }
    
    public void setpaymentSpeed(int paymentSpeed) {
        this.paymentSpeed = paymentSpeed;
    }
    
    public void setexitSpeed(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }
    
    //Cars methods
	private void carTick() {
		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
    		for (int row = 0; row < getNumberOfRows(); row++) {
    			for (int place = 0; place < getNumberOfPlaces(); place++) {
    				Location location = new Location(floor, row, place);
    				Car car = getCarAt(location);
    				if (car != null) {
    					car.tick();
					}
				}           
    		}
		}  	
	}
	
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }
 
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
}
