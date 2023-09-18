package EncapsulationLab.T02SalaryIncrease;

import java.text.DecimalFormat;

public class Person {

    private String firstName;
    private String lastName;
    private int age;

    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double bonus){

        double percentBonus = bonus/100;

        if (this.getAge()<=30){
            this.setSalary(this.getSalary() * (1 + (percentBonus / 2)));
        }else {
            this.setSalary(this.salary * (1 + percentBonus));
        }
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.0####");
        return String.format("%s %s gets %s leva", this.firstName, this.lastName, df.format(this.salary));

    }
}