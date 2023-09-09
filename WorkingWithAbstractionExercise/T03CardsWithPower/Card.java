package WorkingWithAbstractionExercise.T03CardsWithPower;

public class Card {

    private int power;
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
        this.power = this.cardRank.getPower() + this.cardSuit.getPower();
    }

    public void print(){
        System.out.printf("Card name: %s of %s; Card power: %d\n"
                , this.cardRank, this.cardSuit, this.power);
    }
}
