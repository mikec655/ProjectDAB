package logic;

public class Location {

    private int floor;
    private int row;
    private int place;

    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        }
        else {
            return false;
        }
    }

    public String toString() {
        return floor + "," + row + "," + place;
    }

    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    public int getFloor() {
        return floor;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

}
