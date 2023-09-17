package EncapsulationExercises02AnimalFarm;

public class Chicken {

    private String name;
    private int age;


    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {

        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {

        if (age < 0 || age >=15){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }


    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double productPerDay(){
        return this.calculateProductPerDay();
    }


    private double calculateProductPerDay(){

        double eggs;

        if (this.getAge() < 6){
            eggs =  2.00;
        } else if (this.getAge() < 12) {
            eggs = 1.00;
        } else {
            eggs = 0.75;
        }

        return eggs;

    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day."
                , this.getName(), this.getAge(), this.productPerDay());
    }
}
