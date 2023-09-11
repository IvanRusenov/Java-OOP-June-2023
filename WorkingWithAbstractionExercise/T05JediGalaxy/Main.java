package WorkingWithAbstractionExercise.T05JediGalaxy.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] galaxy = initialiseGalaxy(scanner);

        fillGalaxy(galaxy);

        String command = scanner.nextLine();
        long sum = 0;

        while (!"Let the Force be with you".equals(command)) {

            int[] evilStartPosition = getData(scanner.nextLine());

            evilMove(evilStartPosition, galaxy);

            int[] ivoStartPosition = getData(command);

            sum += ivoMove(ivoStartPosition, galaxy);

            command = scanner.nextLine();

        }

        System.out.println(sum);


    }

    private static int[] getData(String command) {
        return Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private static int[][] initialiseGalaxy(Scanner scanner) {
        int[] dimensions = getData(scanner.nextLine());
        int x = dimensions[0];
        int y = dimensions[1];
        return new int[x][y];
    }

    private static void fillGalaxy(int[][] galaxy) {
        int starValue = 0;
        for (int row = 0; row < galaxy.length; row++) {
            for (int col = 0; col < galaxy[0].length; col++) {
                galaxy[row][col] = starValue++;
            }
        }
    }

    private static boolean isInGalaxy(int[][] galaxy, int row, int col) {
        return row >= 0 && col >= 0 && row < galaxy.length && col < galaxy[0].length;
    }

    public static long ivoMove(int[] startPosition, int[][] galaxy) {

        int row = startPosition[0];
        int col = startPosition[1];
        long sum = 0;

        while (row >= 0 && col < galaxy[0].length) {

            if (isInGalaxy(galaxy, row, col)) {
                sum += galaxy[row][col];
            }

            row--;
            col++;

        }

        return sum;

    }

    public static void evilMove(int[] startPosition, int[][] galaxy) {

        int row = startPosition[0];
        int col = startPosition[1];

        while (row >= 0 && col >= 0) {

            if (isInGalaxy(galaxy, row, col)) {
                galaxy[row][col] = 0;
            }

            row--;
            col--;
        }


    }
}
