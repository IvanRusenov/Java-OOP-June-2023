package InterfacesAndAbstractionLab05BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        List<Identifiable> identifiable = new ArrayList<>();

        while (!"End".equals(input)){

            String[] data = input.split("\\s+");

            if (data.length == 2){

                String id = data[0];
                String model = data[1];

                identifiable.add(new Robot(model,id));


            }else if (data.length == 3){
                String name = data[0];
                int age  = Integer.parseInt(data[1]);
                String id = data[2];
                identifiable.add(new Citizen(name, age, id));

            }

            input = scan.nextLine();

        }

        String fakeIds = scan.nextLine();

        System.out.println();

        identifiable.stream().filter(i-> i.getId().endsWith(fakeIds)).forEach( el -> System.out.println(el.getId()));
    }
}
