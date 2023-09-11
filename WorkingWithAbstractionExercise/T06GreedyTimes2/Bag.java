package WorkingWithAbstractionExercise.T06GreedyTimes2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bag {

    private final List<Item> gems;
    private final List<Item> cash;


    private long capacity;
    private long allItemsQuantity;
    private long cashQuantity;
    private long gemQuantity;
    private long goldQuantity;

    public Bag(long capacity) {
        this.setCapacity(capacity);
        this.cashQuantity = 0L;
        this.gemQuantity = 0L;
        this.goldQuantity = 0L;
        this.allItemsQuantity = 0L;
        this.cash = new ArrayList<>();
        this.gems = new ArrayList<>();
    }

    private long getCapacity() {
        return capacity;
    }

    private long getCashQuantity() {
        return cashQuantity;
    }

    private long getGemQuantity() {
        return gemQuantity;
    }

    private long getGoldQuantity() {
        return goldQuantity;
    }

    private long getAllItemsQuantity() {
        return allItemsQuantity;
    }

    private void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    private void setAllItemsQuantity(long allItemsQuantity) {
        this.allItemsQuantity = allItemsQuantity;
    }

    private void setCashQuantity(long cashQuantity) {
        this.cashQuantity += cashQuantity;
    }

    private void setGemQuantity(long gemQuantity) {
        this.gemQuantity += gemQuantity;
    }

    private void setGoldQuantity(long goldQuantity) {
        this.goldQuantity += goldQuantity;
    }

    public void addItemIfIsValid(Item item) {

        switch (item.getType()) {

            case "Cash":
                if (this.getCashQuantity() + item.getQuantity() <= this.getGemQuantity()
                        && this.getAllItemsQuantity() + item.getQuantity() <= this.getCapacity()) {
                    boolean isExist = false;
                    for (Item el : this.cash) {

                        if (el.getName().equals(item.getName())){
                            el.setQuantity(item.getQuantity());
                            isExist = true;
                            break;
                        }
                    }

                    if (!isExist){

                        this.cash.add(item);

                    }
                    this.setCashQuantity(item.getQuantity());

                }
                break;

            case "Gem":
                if (this.getGemQuantity() + item.getQuantity() <= this.getGoldQuantity()
                        && getAllItemsQuantity() + item.getQuantity() <= this.getCapacity()) {
                    boolean isExist = false;
                    for (Item el : this.gems) {

                        if (el.getName().equals(item.getName())){
                            el.setQuantity(item.getQuantity());
                            isExist = true;
                            break;
                        }
                    }

                    if (!isExist){

                        this.gems.add(item);

                    }

                    this.setGemQuantity(item.getQuantity());

                }
                break;

            case "Gold":

                if (this.getAllItemsQuantity() + item.getQuantity() <= this.getCapacity()) {

                    this.setGoldQuantity(item.getQuantity());

                }
                break;

        }

        this.setAllItemsQuantity(this.getCashQuantity() + this.getGemQuantity() + this.getGoldQuantity());

    }


    public void print() {

        StringBuilder sb = new StringBuilder();

        if (this.getGoldQuantity() > 0L) {

            sb.append(String.format("<Gold> $%d", this.getGoldQuantity()));
            sb.append(System.lineSeparator());
            sb.append(String.format("##Gold - %d", this.getGoldQuantity()));
            sb.append(System.lineSeparator());

        }


        if (!this.gems.isEmpty()) {
            sb.append(String.format("<Gem> $%d", this.getGemQuantity())).append(System.lineSeparator());

            this.gems.stream().sorted(Comparator.comparing(Item::getName).thenComparing(Item::getQuantity))
                    .forEach(g -> sb.append(String.format("##%s - %d", g.getName(), g.getQuantity()))
                            .append(System.lineSeparator()));

        }

        if (!this.cash.isEmpty()) {
            sb.append(String.format("<Cash> $%d", this.getCashQuantity())).append(System.lineSeparator());

            this.cash.stream().sorted(Comparator.comparing(Item::getName).reversed().thenComparing(Item::getQuantity))
                    .forEach(c -> sb.append(String.format("##%s - %d", c.getName().toUpperCase(), c.getQuantity()))
                            .append(System.lineSeparator()));

        }

        System.out.println(sb);

    }
}
