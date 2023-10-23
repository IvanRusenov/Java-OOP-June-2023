package magicGame.core;

import magicGame.common.ExceptionMessages;
import magicGame.common.OutputMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.MagicImpl;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }


    @Override
    public String addMagic(String type, String name, int bulletsCount) {

        Magic magic;
        switch (type) {
            case "RedMagic":
                magic = new RedMagic(name, bulletsCount);
                break;
            case "BlackMagic":
                magic = new BlackMagic(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_TYPE);
        }

        magics.addMagic(magic);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGIC, name);

    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {

        Magic magic = magics.findByName(magicName);

        if (magic == null){
            throw new NullPointerException(ExceptionMessages.MAGIC_CANNOT_BE_FOUND);
        }

        Magician magician;

        switch (type) {
            case "Wizard":
                magician = new Wizard(username, health, protection, magic);
                break;
            case "BlackWidow":
                magician = new BlackWidow(username, health, protection, magic);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_TYPE);
        }

        magicians.addMagician(magician);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN,username);

    }

    @Override
    public String startGame() {
        return region.start(magicians.getData());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        List<Magician> sortedMagicians = magicians.getData().stream().sorted(Comparator.comparing(Magician::getHealth).thenComparing(Magician::getUsername).thenComparing(magician -> magician.getClass().getSimpleName())).collect(Collectors.toList());
        sortedMagicians.forEach(m-> sb.append(m.toString()).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
