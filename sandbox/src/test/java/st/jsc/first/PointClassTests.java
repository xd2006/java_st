package st.jsc.first;

import org.apache.commons.math3.util.Precision;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Alex on 28.05.2016.
 */
public class PointClassTests {

    @Test
    public void DistanceCheckRounding()
    {

        Point p1 = new Point(-2.3,8);
        Point p2 = new Point(8.5,0.7);
        double expectedDistance = 13.036;
        Assert.assertEquals(Precision.round(p1.distanceToPoint(p2),3), expectedDistance);
        Assert.assertEquals(Precision.round(p2.distanceToPoint(p1),3), expectedDistance);

    }

    @Test
    public void DistanceCheckPrecise()
    {

        Point p1 = new Point(-10,100);
        Point p2 = new Point(2.5,0.75);
        double expectedDistance = 100.03405670070569;
        Assert.assertEquals(p1.distanceToPoint(p2), expectedDistance);
        Assert.assertEquals(p2.distanceToPoint(p1), expectedDistance);

    }
    @Test
    public void DistanceCheckZeroPoint()
    {

        Point p1 = new Point(0,0);
        Point p2 = new Point(0.5,0.7);
        double expectedDistance = 0.8602325267042626;
        Assert.assertEquals(p1.distanceToPoint(p2), expectedDistance);
        Assert.assertEquals(p2.distanceToPoint(p1), expectedDistance);

    }

    @Test
    public void DistanceCheckRandomThruFunction()
    {
        Point p1 = new Point(ThreadLocalRandom.current().nextDouble(-100,100),ThreadLocalRandom.current().nextDouble(-100,100));
        Point p2 = new Point(ThreadLocalRandom.current().nextDouble(-100,100),ThreadLocalRandom.current().nextDouble(-100,100));
        double expectedDistance = MyFirstProgram.distance(p1,p2);
        Assert.assertEquals(p1.distanceToPoint(p2), expectedDistance);
        Assert.assertEquals(p2.distanceToPoint(p1), expectedDistance);

    }


}
