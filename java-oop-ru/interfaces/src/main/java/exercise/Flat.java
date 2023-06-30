package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getBalconyArea() {
        return this.balconyArea;
    }

    public int getFloor() {
        return this.floor;
    }

    @Override
    public double getArea() {
        return this.area + balconyArea;
    }

    @Override
    public int compareTo(Home anotherHome) {
        if (getArea() == anotherHome.getArea()) {
            return 0;
        }
        return getArea() > anotherHome.getArea() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Квартира площадью " +
                getArea() +
                " метров на " +
                getFloor() +
                " этаже";
    }
}
// END
