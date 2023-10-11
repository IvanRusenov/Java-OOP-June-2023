package InterfacesAndAbstractionExercises05Telephony;

import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Callable, Browsable{

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = new ArrayList<>(numbers);
        this.urls = new ArrayList<>(urls);
    }

    @Override
    public String call() {

       StringBuilder sb = new StringBuilder();

        for (String number : numbers) {
            if (isValidNumber(number)){
                sb.append(String.format("Calling... %s", number)).append(System.lineSeparator());
            }else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }

        }

        return sb.toString();
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();

        for (String url : urls) {
            if (isValidUrl(url)){
                sb.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());

            }else {
                sb.append("Invalid URL!").append(System.lineSeparator());

            }
        }

        return sb.toString();
    }

    private static boolean isValidNumber(String input){

        for (char ch : input.toCharArray()) {
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }

    private static boolean isValidUrl(String input){
        for (char ch : input.toCharArray()) {
            if(Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }





}
