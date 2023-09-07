package WorkingWithAbstractionExercise.T01CardSuit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        if ("Card Suits".equals(command)){
           CardSuits.print();
        }




    }


}
