package bank.entities.loan;

public class MortgageLoan extends BaseLoan{

    private static final int RATE = 3;
    private static final double AMOUNT = 50000.00;


    public MortgageLoan() {
        super(RATE, AMOUNT);
    }
}
