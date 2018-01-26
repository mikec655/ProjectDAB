package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import logic.Car;
import logic.ResCar;
import logic.Location;
import logic.Model;

//CarParkView extends AbstractView
public class CarParkView extends AbstractView{
	private static final long serialVersionUID = -5134000553073183997L;
	private Dimension size;
    private Image carParkImage; 
    //private Color kleur;

    //Voegt het model toe.
	public CarParkView(Model model) {
		super(model);
		size = new Dimension(0, 0);
	}
	
	//Dit geeft de waardes aan dimensies van het frame.
    public Dimension getPreferredSize() {
        return new Dimension(1000, 500);
    }

    
    //Dit tekent de image als het aanwezig is.
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    //Zorgt ervoor dat de CarPark samengesteld wordt.
    public void updateView() {
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            for(int row = 0; row < model.getNumberOfRows(); row++) {
                for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = model.getCarAt(location);
                    Color color = null;
                    if(car instanceof ResCar) {
                    	color = car == null ? Color.BLACK : car.getColor();	
                    }
                    else {
                    	 color = car == null ? Color.white : car.getColor();}
                    
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    //Dit tekent de parkeervakken die nodig zijn voor de CarPark.
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); 
    }
}
