package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private SupplementRepository supplementRepository;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {

        if (!(fieldType.equals("ArtificialTurf") || fieldType.equals("NaturalGrass"))) {
            throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        switch (fieldType) {
            case "ArtificialTurf":
                fields.add(new ArtificialTurf(fieldName));
                break;
            case "NaturalGrass":
                fields.add(new NaturalGrass(fieldName));
                break;
        }


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {

        if (!(type.equals("Powdered") || type.equals("Liquid"))) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        switch (type) {
            case "Powdered":
                supplementRepository.add(new Powdered());
                break;
            case "Liquid":
                supplementRepository.add(new Liquid());
                break;
        }


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement = supplementRepository.findByType(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        field.addSupplement(supplement);
        supplementRepository.remove(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);

    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        if (!(playerType.equals("Men") || playerType.equals("Women"))) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);

        if (playerType.equals("Men") && field.getClass().getSimpleName().equals("ArtificialTurf")) {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }
        if (playerType.equals("Women") && field.getClass().getSimpleName().equals("NaturalGrass")) {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }

        switch (playerType) {
            case "Men":
                field.addPlayer(new Men(playerName, nationality, strength));
                break;
            case "Women":
                field.addPlayer(new Women(playerName, nationality, strength));
                break;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);

    }

    @Override
    public String dragPlayer(String fieldName) {

        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        field.drag();

        return String.format(ConstantMessages.PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, field.getPlayers().stream().mapToInt(Player::getStrength).sum());
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        fields.forEach(f -> sb.append(f.getInfo()).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
