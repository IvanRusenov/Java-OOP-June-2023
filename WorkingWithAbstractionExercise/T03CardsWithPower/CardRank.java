package WorkingWithAbstractionExercise.T03CardsWithPower;

public enum CardRank {
    ACE(14),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private int power;

    CardRank(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public static void print(){
        System.out.println("Card Ranks:");

        for (CardRank cardRank : CardRank.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s\n", cardRank.ordinal(), cardRank.name());
        }
    }


}
