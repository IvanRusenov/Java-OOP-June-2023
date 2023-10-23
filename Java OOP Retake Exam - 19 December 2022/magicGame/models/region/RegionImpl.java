package magicGame.models.region;

import magicGame.common.ExceptionMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region {


    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = magicians.stream().filter(m -> m.getClass().getSimpleName().equals("Wizard")).collect(Collectors.toList());
        List<Magician> blackWidows = magicians.stream().filter(m -> m.getClass().getSimpleName().equals("BlackWidow")).collect(Collectors.toList());


        Wizard wizard = (Wizard) wizards.iterator().next();
        BlackWidow blackWidow = (BlackWidow) blackWidows.iterator().next();

        while (!isAllDead(wizards) && !isAllDead(blackWidows)) {

            if (wizard.getMagic().getBulletsCount() > 0) {

                blackWidow.takeDamage(wizard.getMagic().getBulletsCount());

                if (blackWidow.getHealth() <= 0) {

                    blackWidow.setAlive(false);

                    if (blackWidows.iterator().hasNext()) {
                        blackWidow = (BlackWidow) blackWidows.iterator().next();
                    }else {
                        break;
                    }
                }
            }


            if (blackWidow.getMagic().getBulletsCount() > 0) {

                wizard.takeDamage(blackWidow.getMagic().getBulletsCount());

                if (wizard.getHealth() <= 0) {
                    wizard.setAlive(false);
                    if (wizards.iterator().hasNext()) {
                        wizard = (Wizard) wizards.iterator().next();
                    }else {
                        break;
                    }
                }
            }


        }

        if (isAllDead(wizards)) {
            return "Black widows win!";
        }
        return "Wizards win!";


    }

    private static boolean isAllDead(Collection<Magician> magicians) {
        return magicians.stream().noneMatch(m -> m.getHealth() > 0);
    }
}
