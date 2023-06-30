package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public int getFloorCount() {
        return this.floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
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
        return String.format("%s этажный коттедж площадью %s метров", getFloorCount(), getArea());
    }
}
// END
