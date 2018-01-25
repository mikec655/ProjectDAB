package logic;
 
import java.util.Calendar;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
//implements runnable zorgt ervoor dat er threading komt. Dus de functies toevoegen aan de infiniteloops.
public class Model extends AbstractModel implements Runnable{
    // Deze code kun je beter onder elkaar zetten dat is netter.
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
    private CarQueue leavingqueue;
   
    //Time
    private Calendar time;
    private int minutesRunning;
   
    //Places
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
   
    //Arrivals
    private int[][] arrivalsPass;
    private int[][] arrivalsAdHoc;
    private int[][] arrivalsRes;
   
    //Speed
    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute
   
    //Cars in een multidimensionale array.
    private Car[][][] cars;
   
    //payment
    private double profit;
    private double profitPlus;
    private double profitAv;
    private double gemisteprofit;
   
   //type auto in garage
    private int adhcar;
    private int rescar;
    private int passcar;
    
    //profit per auto
    private double profitadh;
    private double profitres;
   
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
    public void skip(int minutes) {
    	boolean wasRunning = false;
    	if (run) {
    		stop();
    		wasRunning = true;
    	}
    	while(minutes > 0) {
    		 advanceTime();
    	     handleExit();
    	     carTick(); // deze haalt een minuut van de carminutes af.
    	     addPoints();
    	     handleEntrance();
    	     minutes--;
    	}
    	notifyViews();
    	if (wasRunning) start();
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
        leavingqueue = new CarQueue();
        
        //carsinentranceCarQueue
        
        //carsentrancePass
        
        
       
        //Time
        time = Calendar.getInstance();
        minutesRunning = 0;
       
        //Places
        numberOfFloors = 3;
        numberOfRows = 6;
        numberOfPlaces = 30;
        numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
       
        //Arrivals
        arrivalsAdHoc = readArrivalsFile("arrivalsAdHoc.txt");
        arrivalsPass = readArrivalsFile("arrivalsPass.txt");
        arrivalsRes = readArrivalsFile("arrivalsRes.txt");
       
        //Speeds
        enterSpeed = 3;
        paymentSpeed = 7;
        exitSpeed = 5;
        
        //waardes profits etc terug naar 0
        profit = 0;
        profitPlus = 0;
        profitAv = 0;
        adhcar = 0;
        rescar = 0;
        passcar = 0;
        gemisteprofit =0;
        
        //profit per auto
        profitadh = 0;
        profitres = 0;
        
        //Cars  numberOfFloor, numberOfRows, numberOfPlaces
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
       
        try {
            //update view in realTime
            notifyViews();
        } catch(NullPointerException e) {
            //nothing has to happen
        }
    }
    //instellen
    public void reset(int tickPause) {
        this.reset();
        this.tickPause = tickPause;
    }
   
    public void start() {
        if (!run) {
            // new tread wil zeggen dat je een ander stukje programma kan draaien in je programma.
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
        carTick(); // deze haalt een minuut van de carminutes af.
        setMissedProfit() ; //check voor gemiste profit.
        addPoints();
        notifyViews();
        handleEntrance();
    }
   
    private int[][] readArrivalsFile(String file) {
        int[][] arrivals = new int[7][24];
        try {
            InputStream in = Model.class.getClassLoader().getResourceAsStream("arrivals/" + file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null) {
                int day = Integer.parseInt(line.substring(0, 1));
                int hour = Integer.parseInt(line.substring(2, 4));
                arrivals[day][hour] = Integer.parseInt(line.substring(5));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrivals;
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
        int numberOfCars=getNumberOfCars(arrivalsAdHoc);
        addArrivingCars(numberOfCars, AD_HOC);     
        numberOfCars=getNumberOfCars(arrivalsPass);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(arrivalsRes);
        addArrivingCars(numberOfCars, ResCar);  
    }
 
    private void carsEntering(CarQueue queue){
        int i=0;
        Location freeLocation = null;
        // Remove car from the front of the queue and assign to a parking space.
        //if(!null)
        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
          // Car car = queue.removeCar();
            Car car = queue.peekCar();
            // hier checken wat voor car het is.
            if(car instanceof ParkingPassCar) {
            	freeLocation = getFirstpassLocation();
            	if(freeLocation != null) {
            		 car = queue.removeCar();
            		 setCarAt(freeLocation, car);
            		 passcar++;
            	}
            }
            
            else if(car instanceof ResCar){
            	freeLocation = getFirstresLocation();
            	if(freeLocation != null) {
            		 car = queue.removeCar();
            		 setCarAt(freeLocation, car);
            		 rescar++;
            		}
            	}
            
        
            else if(car instanceof AdHocCar){
            	freeLocation = getFirstFreeLocation();	
            	if(freeLocation != null) {
            		 car = queue.removeCar();
            		 setCarAt(freeLocation, car);
            		 adhcar++;
            	}
            }
              i++;
        }
     }
    
    // Add leaving cars to the payment queue.
    private void carsReadyToLeave(){
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
 
    public int gStayMinute() {
        return getMinute();
    }
   
    // Let cars pay. 
    private void carsPaying(){
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
           
            if(car instanceof AdHocCar) {
                profit += car.getPayment();
                profitadh += car.getPaymentADH();
            }
           
            if(car instanceof ResCar) {
                profit += car.getPayment();
                profitres += car.getPaymentres();
            }
            carLeavesSpot(car);
            i++;
            profitAv = profit / minutesRunning * 60;
         }
    }
    
    public double getProfitAv() {
        return profitAv;
    }
   
    public double getProfitPlus() {
        return profitPlus;
    }
    
    public double getProfit() {
        return profit;
    }
    
    public double getMissedProfit() {
    	return gemisteprofit;
    }
    
    // set gemisteprofit naar de totalepayment van alle aut0s in de leavingqueue.
    public void setMissedProfit() {
    	gemisteprofit = leavingqueue.getmissedprofit();
    	entranceCarQueue.getmissedprofit();
    	entrancePassQueue.getmissedprofit();
    } 
    
    // Let cars leave.
    private void carsLeaving(){
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
        minutesRunning++;
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
    
    public int getNumberofLeaving() {
    	return leavingqueue.carsInQueue();
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
 
//    AANPASSEN!!!!!!!!
//    //Getters of arrivals
//    public void getweekDayArrivals(int weekDayArrivals) {
//        this.weekDayArrivals = weekDayArrivals;
//    }
//    
//    public void getweekendArrivals(int weekendArrivals) {
//        this.weekendArrivals = weekendArrivals;
//    }
//    
//    public void getweekDayPassArrivals(int weekDayPassArrivals) {
//        this.weekDayPassArrivals = weekDayPassArrivals;
//    }
//    
//    public void getweekendPassArrivals(int weekendPassArrivals) {
//        this.weekendPassArrivals = weekendPassArrivals;
//    }
//    
//    public void getweekDayResArrivals(int weekDayResArrivals) {
//        this.weekDayResArrivals = weekDayResArrivals;
//    }
//    
//    public void getweekendResArrivals(int weekendResArrivals) {
//        this.weekendResArrivals = weekendResArrivals;
//    }
//    
//    //Setters of arrivals
//    public void setweekDayArrivals(int weekDayArrivals) {
//        this.weekDayArrivals = weekDayArrivals;
//    }
//    
//    public void setweekendArrivals(int weekendArrivals) {
//        this.weekendArrivals = weekendArrivals;
//    }
//    
//    public void setweekDayPassArrivals(int weekDayPassArrivals) {
//        this.weekDayPassArrivals = weekDayPassArrivals;
//    }
//    
//    public void setweekendPassArrivals(int weekendPassArrivals) {
//        this.weekendPassArrivals = weekendPassArrivals;
//    }
//    public void setweekDayResArrivals(int weekDayResArrivals) {
//        this.weekDayResArrivals = weekDayResArrivals;
//    }
//    
//    public void setweekendResArrivals(int weekendResArrivals) {
//        this.weekendResArrivals = weekendResArrivals;
//    }
//    
   
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
   
    //Getters of cars in garage per car + or -
    public int getAmountOfAdHocCars() {
        return adhcar;
    }
    
    public int getAmountOfPassCars() {
        return passcar;
    }
    
    public int getAmountOfResCars() {
        return rescar;
    }
    
    public double getProfitADH() {
    	return profitadh;
    }
    
    public double getProfitres() {
    	return profitres;
    }
    
    public int getamountofPassCarleft() {
    	return leavingqueue.getleavingqueuePassCar();
    }
    public int getamountofResCarleft() {
    	return leavingqueue.getleavingqueueResCar();
    }
	public int getamountofAdHocCarleft() {
		return leavingqueue.getleavingqueueAdHocCar();
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
 
    private int getNumberOfCars(int [][] arrivals){
        Random random = new Random();
        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = arrivals[getDayOfWeek() - 1][getHour()];
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
            	
            	 if(entranceCarQueue.carsInQueue()== 20){
            		 leavingqueue.addCar(new AdHocCar());
            		 //adhcar--
            	 }
            	 else {
            		 entranceCarQueue.addCar(new AdHocCar());
            	 }
          
            }
            break;
         case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	   if(entrancePassQueue.carsInQueue()== 20){
           	 		leavingqueue.addCar(new ParkingPassCar());
            	   }
            	   else {
            		   entrancePassQueue.addCar(new ParkingPassCar());
            	   }
            }
            break; 
         case ResCar:
            for (int i = 0; i < numberOfCars; i++) {
                  if(entranceCarQueue.carsInQueue()== 20){
            		 leavingqueue.addCar(new ResCar());
            	 }
            	 else {
            	   entranceCarQueue.addCar(new ResCar());
            	 }
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
        
        if(car instanceof ParkingPassCar) {
        	passcar--;
        }
        else if(car instanceof ResCar){
        	rescar--;
        	}
        else if(car instanceof AdHocCar){
        	adhcar--;
        }  
        numberOfOpenSpots++;
        return car;
    }
 
    public Location getFirstFreeLocation() {
          for (int floor = 0; floor < 2; floor++) {
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
   
    public Location getFirstresLocation() {
        for (int floor = 0; floor < 2; floor++) {
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