package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.common.enums.BoothType;
import christmasPastryShop.common.enums.CocktailType;
import christmasPastryShop.common.enums.DelicacyType;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.stream.Stream;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;

    private double profit;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        this.profit = 0.0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {

        if (delicacyRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        Delicacy delicacy;

        switch (DelicacyType.valueOf(type)) {
            case Gingerbread:
                delicacy = new Gingerbread(name, price);
                break;
            case Stolen:
                delicacy = new Stolen(name, price);
                break;
            default:
                throw new IllegalArgumentException(String.format("Invalid type %s", type));

        }

        delicacyRepository.add(delicacy);
        return String.format(OutputMessages.DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {

        if (cocktailRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        Cocktail cocktail;

        switch (CocktailType.valueOf(type)) {
            case Hibernation:
                cocktail = new Hibernation(name, size, brand);
                break;
            case MulledWine:
                cocktail = new MulledWine(name, size, brand);
                break;
            default:
                throw new IllegalArgumentException(String.format("Invalid type %s", type));
        }



        cocktailRepository.add(cocktail);
        return String.format(OutputMessages.COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {

        if (boothRepository.getByNumber(boothNumber) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST, boothNumber));
        }

        Booth booth;

        switch (BoothType.valueOf(type)) {
            case OpenBooth:
                booth = new OpenBooth(boothNumber, capacity);
                break;
            case PrivateBooth:
                booth = new PrivateBooth(boothNumber, capacity);
                break;
            default:
                throw new IllegalArgumentException(String.format("Invalid type %s", type));
        }



        boothRepository.add(booth);
        return String.format(OutputMessages.BOOTH_ADDED, boothNumber);

    }

    @Override
    public String reserveBooth(int numberOfPeople) {

        Booth booth = boothRepository.getAll().stream().filter(b -> !b.isReserved() && b.getCapacity() >= numberOfPeople).findFirst().orElse(null);
        if (booth == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        booth.reserve(numberOfPeople);
        return String.format(OutputMessages.BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        profit += bill;
        booth.clear();
        return String.format(OutputMessages.BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(OutputMessages.TOTAL_INCOME, profit);
    }
}
