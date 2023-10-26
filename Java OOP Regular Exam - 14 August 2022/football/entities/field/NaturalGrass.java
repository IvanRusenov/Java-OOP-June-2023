package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.stream.Collectors;

public class NaturalGrass extends BaseField{

    private static final int CAPACITY = 250;

    public NaturalGrass(String name) {
        super(name, CAPACITY);
    }
    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName())).append(System.lineSeparator());

        if (getPlayers().isEmpty()){
            sb.append("Player: none").append(System.lineSeparator());
        }else {
            sb.append(String.format("Player: %s", getPlayers().stream().map(Player::getName).collect(Collectors.joining(" ")))).append(System.lineSeparator());
        }
        sb.append(String.format("Supplement: %d",getSupplements().size())).append(System.lineSeparator());
        sb.append(String.format("Energy: %d", getSupplements().stream().mapToInt(Supplement::getEnergy).sum())).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
