package christmasRaces.repositories;


import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class CarRepository implements Repository<Car> {
    Collection<Car> cars;
    public CarRepository() {
        this.cars = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        return cars.stream().filter(c->c.getModel().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return this.cars;
    }

    @Override
    public void add(Car car) {
        cars.add(car);
    }

    @Override
    public boolean remove(Car car) {
        return cars.remove(car);
    }
}
