package st.jsc.first;

/**
 * Created by Alex on 21.05.2016.
 */
public class Point {

    double x;
    double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double distanceToPoint(Point point)
    {
        return Math.sqrt(Math.pow(x-point.x,2)+Math.pow(y-point.y,2));

    }

}
