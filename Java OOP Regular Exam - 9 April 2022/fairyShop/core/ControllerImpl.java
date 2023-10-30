package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {


    HelperRepository helperRepository;
    PresentRepository presentRepository;
    private int countCraftedPresents;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        countCraftedPresents = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {

        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {

        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        List<Helper> readyHelpers = helperRepository.getModels().stream().filter(h -> h.getEnergy() > 50).collect(Collectors.toList());
        if (readyHelpers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        Shop shop = new ShopImpl();
        Present present = presentRepository.findByName(presentName);
        long countBrokenInstruments = 0;
        while (readyHelpers.iterator().hasNext() && !present.isDone()) {
            Helper helper = readyHelpers.iterator().next();
            shop.craft(present, helper);
           countBrokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
        }

        String isDone = "not done";
        if (present.isDone()) {
            countCraftedPresents++;
            isDone = "done";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.PRESENT_DONE,presentName,isDone));
        sb.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,countBrokenInstruments));

       return sb.toString().trim();

    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!",countCraftedPresents)).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());
        helperRepository.getModels().forEach(h-> sb.append(h.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
