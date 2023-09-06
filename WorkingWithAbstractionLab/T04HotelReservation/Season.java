package WorkingWithAbstractionLab.T04HotelReservation;

public enum Season {

    AUTUMN("Autumn",1),
    SPRING("Spring",2),
    WINTER("Winter",3),
    SUMMER("Summer",4);

    private String name;
    private int priceFactor;

    Season(String name, int priceFactor) {
        this.name = name;
        this.priceFactor = priceFactor;
    }



    public String getName() {
        return name;
    }

    public int getPriceFactor() {
        return priceFactor;
    }
}
