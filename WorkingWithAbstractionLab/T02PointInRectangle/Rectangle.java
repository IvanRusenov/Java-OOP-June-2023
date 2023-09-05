package WorkingWithAbstractionLab.T02PointInRectangle;

public class Rectangle {

    private Point bottomLeftPoint;
    private Point topRightPoint;

    public Rectangle(Point bottomLeftPoint, Point topRightPoint) {
        this.bottomLeftPoint = bottomLeftPoint;
        this.topRightPoint = topRightPoint;
    }

    public boolean contains(Point point) {

        return point.getX() >= this.bottomLeftPoint.getX()
                && point.getX() <= this.topRightPoint.getX()
                && point.getY() >= this.bottomLeftPoint.getY()
                && point.getY() <= this.topRightPoint.getY();

    }


}
