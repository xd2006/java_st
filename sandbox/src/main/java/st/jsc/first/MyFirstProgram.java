package st.jsc.first;

import java.util.Scanner;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("This program calculates a distance between 2 points");
        System.out.println("Please enter coordinate X of the 1st point and press Enter");
        Scanner sc = new Scanner(System.in);
        double x = readValueFromConsole(sc);
        System.out.println("Please enter coordinate Y of the 1st point and press Enter");
        double y = readValueFromConsole(sc);
        Point point1 = new Point(x, y);
        System.out.println("Please enter coordinate X of the 2nd point and press Enter");
        x = readValueFromConsole(sc);
        System.out.println("Please enter coordinate Y of the 2nd point and press Enter");
        y = readValueFromConsole(sc);
        sc.close();
        Point point2 = new Point(x, y);
        System.out.println("Coordinates of the 1st point: X = " + point1.x + " Y = " + point1.y);
        System.out.println("Coordinates of the 2nd point: X = " + point2.x + " Y = " + point2.y);

        System.out.println("Distance between points calculated using function = " + distance(point1, point2));
        System.out.println("Distance between points calculated using Point class method = " + point1.distanceToPoint(point2));

    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double readValueFromConsole(Scanner sc) {
        double value = 0;
        boolean read = false;
        while (!read) {
            try {
                value = sc.nextDouble();
                sc.nextLine();
                read = true;
            } catch (Exception e) {
                System.out.println("Incorrect value. Please retry");
                sc.nextLine();
            }
        }
        return value;
    }

}