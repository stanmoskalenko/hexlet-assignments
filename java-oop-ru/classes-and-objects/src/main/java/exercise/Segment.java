package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return this.beginPoint;
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public Point getMidPoint() {
        var bPoint = this.getBeginPoint();
        var ePoint = this.getEndPoint();
        var midPointX = (bPoint.getX() + ePoint.getX()) / 2;
        var midPointY = (bPoint.getY() + ePoint.getY()) / 2;

        return new Point(midPointX, midPointY);
    }
}
// END
