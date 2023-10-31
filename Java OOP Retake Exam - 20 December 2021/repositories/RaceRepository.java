package christmasRaces.repositories;

import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> races;
    public RaceRepository() {
        this.races = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return races.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return this.races;
    }

    @Override
    public void add(Race race) {
        races.add(race);
    }

    @Override
    public boolean remove(Race race) {
        return races.remove(race);
    }
}
