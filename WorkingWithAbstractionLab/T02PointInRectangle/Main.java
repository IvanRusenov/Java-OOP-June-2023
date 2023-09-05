package WorkingWithAbstractionLab.T02PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int[] rectangleCoordinates = getCoordinates(scan);

        Point bottomLeft = new Point(rectangleCoordinates[0], rectangleCoordinates[1]);
        Point topRight = new Point(rectangleCoordinates[2], rectangleCoordinates[3]);

        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int numberOfPointsToCheck = Integer.parseInt(scan.nextLine());

        while (numberOfPointsToCheck-- > 0){

            int[] pointCoordinates = getCoordinates(scan);

            Point point = new Point(pointCoordinates[0], pointCoordinates[1]);

            System.out.println(rectangle.contains(point));
        }

    }

    private static int[] getCoordinates(Scanner scan) {
        return Arrays.stream(scan.nextLine()
                .split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
}
