package st.jsc.first;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.math3.util.Precision;


/**
 * Created by Alex on 28.05.2016.
 */
public class PointClassTests {

    @Test
    public void DistanceCheckRounding()
    {

        Point p1 = new Point(-2.3,8);
        Point p2 = new Point(8.5,0.7);
        Assert.assertEquals(Precision.round(p1.distanceToPoint(p2),3), 13.036);
        Assert.assertEquals(Precision.round(p2.distanceToPoint(p1),3), 13.036);

    }

    @Test
    public void DistanceCheckPrecise()
    {

        Point p1 = new Point(-2.3,8);
        Point p2 = new Point(8.5,0.7);
        Assert.assertEquals(Precision.round(p1.distanceToPoint(p2),3), 13.036);
        Assert.assertEquals(Precision.round(p2.distanceToPoint(p1),3), 13.036);

    }
    @Test
    public void DistanceCheckZeroPoint()
    {

        Point p1 = new Point(-2.3,8);
        Point p2 = new Point(8.5,0.7);
        Assert.assertEquals(Precision.round(p1.distanceToPoint(p2),3), 13.036);
        Assert.assertEquals(Precision.round(p2.distanceToPoint(p1),3), 13.036);

    }


}
