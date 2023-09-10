package WorkingWithAbstractionExercise.T04TrafficLights;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        TrafficLite[] trafficLites = getTrafficLites(scan);

        int timesToUpdate = Integer.parseInt(scan.nextLine());

        while (timesToUpdate-- > 0) {

            for (TrafficLite trafficLite : trafficLites) {
                trafficLite.update();
                trafficLite.print();
            }

            System.out.println();

        }

    }

    private static TrafficLite[] getTrafficLites(Scanner scan) {
        return Arrays.stream(scan.nextLine()
                        .split("\\s+"))
                .map(Color::valueOf)
                .map(TrafficLite::new)
                .toArray(TrafficLite[]::new);
    }
}
