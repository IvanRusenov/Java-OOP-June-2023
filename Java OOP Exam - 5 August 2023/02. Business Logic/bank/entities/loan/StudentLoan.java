package bank.entities.loan;

public class StudentLoan extends BaseLoan{

    private static final int RATE = 1;
    private static final double AMOUNT = 10000.00;


    public StudentLoan() {
        super(RATE, AMOUNT);
    }
}
