package christmasRaces.repositories;

import christmasRaces.entities.drivers.Driver;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
public class DriverRepository implements Repository<Driver> {
    private Collection<Driver> drivers;
    public DriverRepository() {
        this.drivers = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        return drivers.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return this.drivers;
    }

    @Override
    public void add(Driver driver) {
        drivers.add(driver);
    }

    @Override
    public boolean remove(Driver driver) {
        return drivers.remove(driver);
    }
}
