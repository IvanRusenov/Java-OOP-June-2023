package WorkingWithAbstractionLab.T04HotelReservation;

public enum DiscountType {

    VIP("VIP",0.8),
    SECOND_VISIT("SecondVisit", 0.9),
    NONE("None", 1);

    private double discount;
    private String name;

    DiscountType(String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public String getName() {
        return this.name;
    }
}
