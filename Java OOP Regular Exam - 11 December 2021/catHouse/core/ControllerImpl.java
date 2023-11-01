package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        if (!(type.equals("ShortHouse") || type.equals("LongHouse"))) {
            throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }

        switch (type) {
            case "ShortHouse":
                houses.add(new ShortHouse(name));
                break;
            case "LongHouse":
                houses.add(new LongHouse(name));
                break;
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {

        if (!(type.equals("Ball") || type.equals("Mouse"))) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }

        switch (type) {
            case "Ball":
                toys.buyToy(new Ball());
                //todo check if must remove price
                break;
            case "Mouse":
                toys.buyToy(new Mouse());
                break;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        Toy toy = toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }

        house.buyToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);

        if (!(catType.equals("ShorthairCat") || catType.equals("LonghairCat"))) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        if (house.getClass().getSimpleName().equals("LongHouse") && catType.equals("ShorthairCat")) {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }
        if (house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("LonghairCat")) {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }

        switch (catType) {
            case "ShorthairCat":
                house.addCat(new ShorthairCat(catName, catBreed, price));
                break;
            case "LonghairCat":
                house.addCat(new LonghairCat(catName, catBreed, price));
                break;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);

    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        house.getCats().forEach(Cat::eating);
        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        double sumOfCatPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double sumOfToyPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE,houseName, sumOfToyPrice + sumOfCatPrice);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        houses.forEach(h-> sb.append(h.getStatistics()).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
