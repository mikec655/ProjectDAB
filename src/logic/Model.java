package logic;

import java.util.Calendar;
import java.util.Random;

//implements runnable zorgt ervoor dat er threading komt. Dus de functies toevoegen aan de infiniteloops.
public class Model extends AbstractModel implements Runnable{
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String ResCar = "3";
	
	//Run variables
	private boolean run;
	private int tickPause;
	
	//Queues
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    //Time
    Calendar time;
    
    //Places
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    
    //Arrivals
    private int weekDayArrivals; // average number of arriving cars per hour
    private int weekendArrivals; // average number of arriving cars per hour
    private int weekDayPassArrivals; // average number of arriving cars per hour
    private int weekendPassArrivals; // average number of arriving cars per hour
    private int weekDayResArrivals; // average number of arriving reserved cars per hour
    private int weekendResArrivals; // average number of arriving reserved cars per hour
    
    //Speed
    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute
    
    //Cars
    private Car[][][] cars;
	
    //Constructor
	public Model() {
		reset();
	}
	
	//Run methods
	public void run() {
		run = true;
		while(run) {
			tick();
			try {
				Thread.sleep(tickPause);
			} catch (Exception e) {} 
		}
	}
	
	public void reset() {
		//Run variables
		run = false;
		tickPause = 100;
		
		//Queues
		entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        //Time 
        time = Calendar.getInstance();
        
        //Places
        numberOfFloors = 3;
        numberOfRows = 6;
        numberOfPlaces = 30;
        numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        
        //Arrivals
        weekDayArrivals = 200;
        weekDayPassArrivals= 50;
        weekDayResArrivals = 20;
        weekendArrivals = 100; 
        weekendPassArrivals = 5; 
        weekendResArrivals = 10;
        
        //Speeds
        enterSpeed = 3; 
        paymentSpeed = 7; 
        exitSpeed = 5; 
        
        //Cars
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
        try {
        	notifyViews();
        } catch(NullPointerException e) {
        	//nothing has to happen
        }
	}
	
	public void reset(int tickPause) {
		this.reset();
		this.tickPause = tickPause;
	}
	
	public void start() {
		if (!run) {
			new Thread(this).start();
		}
	}
	
	public void stop() {
		run = false;
	}
	
	public void setTickPause(int tickPause) {
		this.tickPause = tickPause;
	}
    
    private void tick() {
    	advanceTime();
    	handleExit();
    	carTick();
    	notifyViews();
    	handleEntrance();
    }

    //Queues
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
        numberOfCars=getNumberOfCars(weekDayResArrivals, weekendResArrivals);
        addArrivingCars(numberOfCars, ResCar);  
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        Location freeLocation = null;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            // hier checken wat voor car het is.
            if(car instanceof ParkingPassCar) {
            freeLocation = getFirstpassLocation();
            }
            else{
            freeLocation = getFirstFreeLocation();
            }
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
        time.add(Calendar.MINUTE, 1);
	}
	
	//Getters of time
	public int getMinute() {
        return time.get(Calendar.MINUTE);
    }
	
	public int getHour() {
        return time.get(Calendar.HOUR_OF_DAY);
    }
	
	public int getDay() {
        return time.get(Calendar.DAY_OF_MONTH);
    }
	
	public int getDayOfWeek() {
        return time.get(Calendar.DAY_OF_WEEK);
    }
	
	public int getMonth() {
		return time.get(Calendar.MONTH);
	}
	
	public int getYear() {
        return time.get(Calendar.YEAR);
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
    
    public void getweekDayResArrivals(int weekDayResArrivals) {
        this.weekDayResArrivals = weekDayResArrivals;
    }
    
    public void getweekendResArrivals(int weekendResArrivals) {
        this.weekendResArrivals = weekendResArrivals;
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
    public void setweekDayResArrivals(int weekDayResArrivals) {
        this.weekDayResArrivals = weekDayResArrivals;
    }
    
    public void setweekendResArrivals(int weekendResArrivals) {
        this.weekendResArrivals = weekendResArrivals;
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
        int averageNumberOfCarsPerHour = getDayOfWeek() == 1 || getDayOfWeek() == 7
                ? weekend
                : weekDay;

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
    	case ResCar:
    		for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new ResCar());
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
    
    public Location getFirstpassLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
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
