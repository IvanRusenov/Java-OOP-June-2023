package WorkingWithAbstractionLab.T04HotelReservation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] reservationData = scan.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(reservationData[0]);
        int numberOfDays = Integer.parseInt(reservationData[1].toUpperCase());



        Season season = Season.valueOf(reservationData[2].toUpperCase());
        DiscountType discountType = DiscountType.valueOf(reservationData[3].toUpperCase());

        PriceCalculator priceCalculator = new PriceCalculator(pricePerDay,numberOfDays,season,discountType);
        System.out.printf("%.2f\n", priceCalculator.calculate());







    }
}
