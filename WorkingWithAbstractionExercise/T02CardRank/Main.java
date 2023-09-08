package WorkingWithAbstractionExercise.T02CardRank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        if("Card Ranks".equals(command)){
            CardRank.print();
        }
    }

}
