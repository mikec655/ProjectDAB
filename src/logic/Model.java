package logic;
 
import java.util.Calendar;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
//Implements runnable zorgt ervoor dat er threading komt. 
//Dus de functies toevoegen aan de infiniteloops.
public class Model extends AbstractModel implements Runnable{
    private static final String AD_HOC = "1";
    private static final String PASS = "2";
    private static final String ResCar = "3";
   
    //Run variables.
    private boolean run;
    private int tickPause;
   
    //Queues.
    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private CarQueue leavingqueue;
    private int queueSize;
   
    //Time.
    private Calendar time;
    private int minutesRunning;
   
    //Places.
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
   
    //Arrivals.
    private int[][] arrivalsPass;
    private int[][] arrivalsAdHoc;
    private int[][] arrivalsRes;
    private double syntJazz;
   
    //Speed.
    private int enterSpeed; //Aantal auto's dat per minuut naar binnen mogen.
    private int paymentSpeed; //Aantal auto's dat mag betalen per minuut.
    private int exitSpeed; //Aantal auto's dat weg mag per minuut.
   
    //Cars in een multidimensionale array.
    private Car[][][] cars;
   
    //payment.
    private double profit;
    private double profitPlus;
    private double profitAv;
    private double gemisteprofit;
   
   //type auto in garage.
    private int adhcar;
    private int rescar;
    private int passcar;
    
    //profit per auto.
    private double profitadh;
    private double profitres;
    
    
   
    //Constructor.
    public Model() {
        reset();
    }
   
    //Run de methodes.
    public void run() {
        run = true;
        while(run) {
            tick();
            try {
                Thread.sleep(tickPause);
            } catch (Exception e) {}
        }
    }
    //Skipt een minuut.
    public void skip(int minutes) {
    	boolean wasRunning = false;
    	if (run) {
    		stop();
    		wasRunning = true;
    	}
    	while(minutes > 0) {
    		 advanceTime();
    	     handleExit();
    	     carTick(); //Deze haalt een minuut van de carminutes af.
    	     setMissedProfit() ; //check voor gemiste profit.
    	     addPoints();
    	     handleEntrance();
    	     minutes--;
    	}
    	notifyViews();
    	if (wasRunning) start();
    }
    
    //Reset alles
    public void reset() {
        //Run variables
        run = false;
        tickPause = 100;
       
        //Queues.
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        leavingqueue = new CarQueue();
        queueSize = 20;
       
        //Time.
        time = Calendar.getInstance();
        minutesRunning = 0;
       
        //Places.
        numberOfFloors = 3;
        numberOfRows = 6;
        numberOfPlaces = 30;
        numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
       
        //Arrivals.
        arrivalsAdHoc = readArrivalsFile("arrivalsAdHoc.txt");
        arrivalsPass = readArrivalsFile("arrivalsPass.txt");
        arrivalsRes = readArrivalsFile("arrivalsRes.txt");
        syntJazz = 1.0;
       
        //Speeds.
        enterSpeed = 3;
        paymentSpeed = 7;
        exitSpeed = 5;
        
        //waardes profits etc terug naar 0.
        profit = 0;
        profitPlus = 0;
        profitAv = 0;
        adhcar = 0;
        rescar = 0;
        passcar = 0;
        gemisteprofit =0;
        
        //profit per auto.
        profitadh = 0;
        profitres = 0;
        
        //Cars  numberOfFloor, numberOfRows, numberOfPlaces.
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
       
        try {
            //update view in realTime.
            notifyViews();
        } catch(NullPointerException e) {
            //Niks veranderd.
        }
    }
    
    //Instellen
    public void reset(int tickPause) {
        this.reset();
        this.tickPause = tickPause;
    }
   
    //Start het programma.
    public void start() {
        if (!run) {
            // new tread wil zeggen dat je een ander stukje programma kan draaien in je programma.
            new Thread(this, "Thread-1").start();
        }
    }
    //Stopt het programma met runnen.
    public void stop() {
        run = false;
    }
   
    //Pauseert een tick.
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
   
    //Dit is dat auto's 24/7 binnen kunnen komen.
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
 
    //Dit is voor de queues
    private void handleEntrance(){
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);    
    }
   
    //Om te controleren voor de auto's die weggaan.
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
   
    //Hiermee zie je welke auto's binnen komen.
    private void carsArriving(){
        int numberOfCars=getNumberOfCars(arrivalsAdHoc);
        addArrivingCars(numberOfCars, AD_HOC);     
        numberOfCars=getNumberOfCars(arrivalsPass);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(arrivalsRes);
        addArrivingCars(numberOfCars, ResCar);  
    }
    //Hiermee kan je zien welke auto's naar binnen gaan.
    private void carsEntering(CarQueue queue){
        int i=0;
        Location freeLocation = null;
        //Verwijdert een auto aan de begin van de queue en geeft het een plaats.
       
        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
          
            Car car = queue.peekCar();
            //Hier checken wat voor car het is.
            if(car instanceof ParkingPassCar) {
            	freeLocation = getFirstpassLocation();
            	if(freeLocation != null) {
            		 car = queue.removeCar();
            		 setCarAt(freeLocation, car);
            		 passcar++;
            	}
            }
            //Als de auto wel een reservering heeft.
            else if(car instanceof ResCar){
            	freeLocation = getFirstresLocation();
            	if(freeLocation != null) {
            		 car = queue.removeCar();
            		 setCarAt(freeLocation, car);
            		 rescar++;
            		}
            	}
            
            //Als een auto geen reservering heeft.
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
    
    //Voegt auto's toe aan de queue voor mensen die moeten betalen en weggaan.
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
    //returned het aantal minuten van een auto die gebleven is.
    public int gStayMinute() {
        return getMinute();
    }
   
    //Dit laat de auto's betalen.
    private void carsPaying(){
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            //Als een auto normaal is.
            if(car instanceof AdHocCar) {
                profit += car.getPayment();
                profitadh += car.getPaymentADH();
            }
            //Als een auto een reservering heeft.
            if(car instanceof ResCar) {
                profit += car.getPayment();
                profitres += car.getPaymentres();
            }
            carLeavesSpot(car);
            i++;
            profitAv = profit / minutesRunning * 60;
         }
    }
    //returned de average profit.
    public double getProfitAv() {
        return profitAv;
    }
    //returned de profitPlus.
    public double getProfitPlus() {
        return profitPlus;
    }
    //returned de profit.
    public double getProfit() {
        return profit;
    }
    //returned de missing profit.
    public double getMissedProfit() {
    	return gemisteprofit;
    }
    
    //set gemiste profit naar de totalepayment van alle auto's in de leavingqueue.
    public void setMissedProfit() {
    	gemisteprofit = leavingqueue.getmissedprofit();
    	entranceCarQueue.getmissedprofit();
    	entrancePassQueue.getmissedprofit();
    } 
    
    //Laat de auto's verlaten.
    private void carsLeaving(){
       int i=0;
        while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
        }  
    }
   
    //Time methode
    private void advanceTime(){
        //Tijd gaat met 1 minuut omhoog.
        time.add(Calendar.MINUTE, 1);
        minutesRunning++;
    }
   
    //Getters of time
    //returned het aantal minuten in een kalender.
    public int getMinute() {
        return time.get(Calendar.MINUTE);
    }
    //returned het aantal uur in een kalender.
    public int getHour() {
        return time.get(Calendar.HOUR_OF_DAY);
    }
    //returned de kalender dagen.
    public int getDay() {
        return time.get(Calendar.DAY_OF_MONTH);
    }
    // returned de kalender weken.
    public int getDayOfWeek() {
        return time.get(Calendar.DAY_OF_WEEK);
    }
    //returned de kalender maanden.
    public int getMonth() {
        return time.get(Calendar.MONTH);
    }
    //returned de kalender jaren.
    public int getYear() {
        return time.get(Calendar.YEAR);
    }
   
    //Getters of places
    //returned het aantal floors die er zijn.
    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    //returned het aantal rijen die er zijn.
    public int getNumberOfRows() {
        return numberOfRows;
    }
    //returned het aantal plaatsen die er zijn.
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }
    //returned het aantal open spots.
    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }
    //returned het aantal auto's die leaven.
    public int getNumberofLeaving() {
    	return leavingqueue.carsInQueue();
    }
    
     
    //Setters of places
    //set de nummer van het aantal floors die er zijn.
    public void setNumberOfFloors(int numberofFloors) {
        this.numberOfFloors = numberofFloors;
    }
    //set de nummer van het aantal rijen die er zijn.
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
    //set de nummer van de plaatsen die er zijn.
    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }
    //set de nummer van open spots.
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
    //Getter van de paymentSpeed.
    public void getpaymentSpeed(int paymentSpeed) {
        this.paymentSpeed = paymentSpeed;
    }
    //Getter van de exitSpeed.
    public void getexitSpeed(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }
    //Getter van de synthJazz.
    public void getsyntJazz(double syntJazz) {
        this.syntJazz=syntJazz ;
    }
   
    //Setters of speeds
    //set de enteringSpeed.
    public void setenterSpeed(int enterSpeed) {
        this.enterSpeed = enterSpeed;
    }
    //set de paymentSpeed.
    public void setpaymentSpeed(int paymentSpeed) {
        this.paymentSpeed = paymentSpeed;
    }
    //Set de exitSpeed.
    public void setexitSpeed(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }
    //set de synthJazz?
    public void setsyntJazz(double syntJazz) {
        this.syntJazz=syntJazz ;
    }
    //set de queueSize
    public void setQueueSize(int queueSize) {
    	this.queueSize = queueSize;
    }
    public int getQueueSize() {
    	return queueSize;
    }
    
    //returned de amount van hoeveel normale auto's er zijn.
    public int getAmountOfAdHocCars() {
        return adhcar;
    }
    //returned de amount van hoeveel abonnement auto's er zijn.
    public int getAmountOfPassCars() {
        return passcar;
    }
    //returned de amount van hoeveel gereserveerde auto's er zijn.
    public int getAmountOfResCars() {
        return rescar;
    }
    //returned de profit van een normale auto.
    public double getProfitADH() {
    	return profitadh;
    }
    //returned de profit van reserveer auto's.
    public double getProfitres() {
    	return profitres;
    }
    //getters om auto hoeveelheden op te halen.
    public int getamountofPassCarleft() {
    	return leavingqueue.getqueuePassCar();
    }
    //returned hoeveel auto's die een reservering hebben uit de queue zijn gegaan.
    public int getamountofResCarleft() {
    	return leavingqueue.getqueueResCar();
    }
    //returned hoeveel auto's die normaal zijn uit de queue zijn gegaan.
	public int getamountofAdHocCarleft() {
		return leavingqueue.getqueueAdHocCar();
	}
	//returned hoeveel auto's die een abonnement hebben die in de abonnemnthoudersqueue staat.
	public int getamountofPassCarinPassqueue() {
    	return entrancePassQueue.getqueuePassCar();
    }
	//returned hoeveel auto's die een abonnement hebben die in de queue staat.
    public int getamountofPassCarinEntrancequeue() {
    	return entranceCarQueue.getqueuePassCar();
    }
    //returned hoeveel auto's die een reservering hebben die in de queue staat.
    public int getamountofResCarinEntrancequeue() {
    	return entranceCarQueue.getqueueResCar();
    }
    //returned hoeveel auto's die normaal zijn die in de queue staat.
	public int getamountofAdHocCarinEntrancequeue() {
		return entranceCarQueue.getqueueAdHocCar();
	}
	
	//returned hoeveel auto's die normaal zijn die in de queue staan.
	public int getentranceCarQueuesize() {
		return entranceCarQueue.carsInQueue();
	}
	//returned hoeveel auto's die een abonnement hebben die in de queue staan.
	public int getentrancePassQueuesize() {
		return entrancePassQueue.carsInQueue();
	}
    
    //Methode voor de auto's.
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

    //Kijkt hoeveel auto's er aanwezig zijn.
    private int getNumberOfCars(int [][] arrivals){
        Random random = new Random();
        //Kijkt naar de average nummer van auto's die er dat uur geweest zijn.
        double averageNumberOfCarsPerHour = arrivals[getDayOfWeek() - 1][getHour()] * syntJazz;
        //Kijkt naar de average nummer van auto's die er in een minuut geweest zijn.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);  
    }
   
    //Voegt auto's toe aan een wachtrij, als er meer dan 20 auto's in de wachtrij zitten dan rijden ze door.
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
   
     switch(type) {
        case AD_HOC:
            for (int i = 0; i < numberOfCars; i++) {
            	
            	 if(entranceCarQueue.carsInQueue()== queueSize){
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
            	   if(entrancePassQueue.carsInQueue()== queueSize){
           	 		leavingqueue.addCar(new ParkingPassCar());
            	   }
            	   else {
            		   entrancePassQueue.addCar(new ParkingPassCar());
            	   }
            }
            break; 
         case ResCar:
            for (int i = 0; i < numberOfCars; i++) {
                  if(entranceCarQueue.carsInQueue()== queueSize){
            		 leavingqueue.addCar(new ResCar());
            	 }
            	 else {
            	   entranceCarQueue.addCar(new ResCar());
            	 }
            }
            
            break;
        }
    }
   
    //Verwijdert een auto van een plek.
    private void carLeavesSpot(Car car){
    	try {
    		removeCarAt(car.getLocation());
        	exitCarQueue.addCar(car);
    	} catch(Exception ex) {
    		
    	}
    }
   
    //Haalt een auto op van een locatie.
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }
 
    //Plaatst een auto op een locatie.
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
 
    //Verwijdert een auto van de plaatst waar die staat en kijkt ook welke het is.
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
 
    //Haalt een locatie op voor waar een auto die geen reservering of abonnement heeft geplaatst kan worden.
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
   
    //Haalt een locatie op waar de eerste auto kan parkeren die een abonnement heeft.
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
   
    //Haalt de locatie op waar een gereserveerde auto kan gaan parkeren.
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
 
   //Haalt de eerste auto op die de uit de garage moet.
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
 
   //Kijkt of er een auto op de locatie geplaatst kan worden.
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