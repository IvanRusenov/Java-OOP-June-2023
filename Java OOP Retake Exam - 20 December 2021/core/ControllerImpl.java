package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = new DriverRepository();
        this.carRepository = new CarRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driverName) {

        if (driverRepository.getByName(driverName) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }

        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (!(type.equals("Sports") || type.equals("Muscle"))) {
            throw new IllegalArgumentException(String.format("Invalid Car type - %s", type));
        }

        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }

        switch (type) {
            case "Sports":
                carRepository.add(new SportsCar(model, horsePower));
                break;
            case "Muscle":
                carRepository.add(new MuscleCar(model, horsePower));
                break;
        }


        return String.format(OutputMessages.CAR_CREATED,type,model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        if (driverRepository.getByName(driverName) == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));
        }
        if (carRepository.getByName(carModel)==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND,carModel));
        }
        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);
        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED,driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName)==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }

        if (driverRepository.getByName(driverName)==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));
        }

        Race race = raceRepository.getByName(raceName);
        Driver driver = driverRepository.getByName(driverName);
        race.addDriver(driver);


        return String.format(OutputMessages.DRIVER_ADDED,driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        if (raceRepository.getByName(raceName)==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        Race race = raceRepository.getByName(raceName);
        if (race.getDrivers().stream().filter(Driver::getCanParticipate).count()<3){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,3));
        }

        List<Driver> sortedDrivers = race.getDrivers().stream()
                .sorted((f, s) -> Double.compare(s.getCar().calculateRacePoints(race.getLaps()), f.getCar().calculateRacePoints(race.getLaps())))
                .limit(3)
                .collect(Collectors.toList());
        raceRepository.remove(race);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Driver %s wins %s race.",sortedDrivers.get(0).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format("Driver %s wins %s race.",sortedDrivers.get(1).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format("Driver %s wins %s race.",sortedDrivers.get(2).getName(),raceName)).append(System.lineSeparator());

        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {

        if (raceRepository.getByName(name)!=null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,name));
        }
        raceRepository.add(new RaceImpl(name,laps));
        return String.format(OutputMessages.RACE_CREATED,name);
    }
}
