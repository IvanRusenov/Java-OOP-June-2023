package EncapsulationExercises03ShoppingSpree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {

    private String name;
    private double money;
    private final List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {

        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return this.name;
    }


    public List<Product> getProducts() {
//
//        if (this.products.isEmpty()){
//            throw new IllegalArgumentException(String.format("%s – Nothing bought",this.getName()));
//        }

        return Collections.unmodifiableList(products);
    }

    public void buyProduct (Product product){

        if (this.money < product.getCost()){
            throw new IllegalArgumentException( String.format("%s can't afford %s", this.getName(), product.getName()));
        }else {
            System.out.printf("%s bought %s", this.getName(), product.getName());
            this.products.add(product);
        }

    }
}
