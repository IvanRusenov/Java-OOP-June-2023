package WorkingWithAbstractionExercise.T06GreedyTimes2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Bag bag = new Bag(Long.parseLong(scan.nextLine()));
        String[] input = readInput(scan.nextLine());

        for (String element : input) {

            String itemType = defineType(element);

            if (itemType != null) {

                String itemName = element.split("\\s+")[0];
                int itemQuantity = Integer.parseInt(element.split("\\s+")[1]);
                Item item = new Item(itemName, itemQuantity, itemType);
                bag.addItemIfIsValid(item);

            }

        }

        bag.print();

    }

    private static String[] readInput(String line) {

        return line.split("\\s+(?=\\D+)");

    }

    private static String defineType(String element) {

        String itemName = element.split("\\s+")[0];

        if (itemName.length() == 3) {

            return "Cash";

        } else if (itemName.toLowerCase().endsWith("gem")) {

            return "Gem";

        } else if (itemName.equalsIgnoreCase("gold")) {

            return "Gold";

        }

        return null;

    }
}



