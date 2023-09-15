package EncapsulationLab.T04FirstAndReserveTeam;

import java.text.DecimalFormat;

class Person {

    private String firstName;
    private String lastName;
    private int age;

    private double salary;


    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {



        if (firstName.length() >= 3) {
            this.firstName = firstName;
        } else {
            System.out.println("First name cannot be less than 3 symbols");
        }

    }

    public String getLastName() {
        return this.lastName;
    }
    private void setLastName(String lastName) {

        if (lastName.length() < 3) {
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {

        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {

        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    public void increaseSalary(double bonus) {

        double percentBonus = bonus / 100;

        if (this.getAge() <= 30) {
            this.setSalary(this.getSalary() * (1 + (percentBonus / 2)));
        } else {
            this.setSalary(this.salary * (1 + percentBonus));
        }
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.0####");
        return String.format("%s %s gets %s leva", this.firstName, this.lastName, df.format(this.salary));

    }
}
