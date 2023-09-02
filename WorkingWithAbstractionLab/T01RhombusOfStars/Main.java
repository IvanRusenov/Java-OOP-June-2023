package WorkingWithAbstractionLab.T01RhombusOfStars;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());


        for (int i = 0; i < n; i++) {

            for (int j = 1; j < n - i; j++) {
                System.out.print(" ");

            }

            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
            }

            System.out.println();


        }

        for (int i = n-2; i >= 0; i--) {

            for (int j = 1; j < n - i; j++) {
                System.out.print(" ");

            }

            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
            }

            System.out.println();

        }

    }

}

