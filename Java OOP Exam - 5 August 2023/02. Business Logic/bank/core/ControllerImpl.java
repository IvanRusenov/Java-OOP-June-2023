package bank.core;

import bank.common.ConstantMessages;
import bank.common.ExceptionMessages;
import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private LoanRepository loanRepository;
    private Collection<Bank> banks;

    public ControllerImpl() {
        this.loanRepository = new LoanRepository();
        this.banks = new ArrayList<>();
    }

    @Override
    public String addBank(String type, String name) {

        if (!(type.equals("CentralBank") || type.equals("BranchBank"))) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BANK_TYPE);
        }

        switch (type) {
            case "CentralBank":
                banks.add(new CentralBank(name));
                break;
            case "BranchBank":
                banks.add(new BranchBank(name));
                break;
        }


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE,type);
    }

    @Override
    public String addLoan(String type) {

        if (!(type.equals("StudentLoan") || type.equals("MortgageLoan"))){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);
        }

        switch (type){
            case "StudentLoan":
                loanRepository.addLoan(new StudentLoan());
                break;
            case "MortgageLoan":
                loanRepository.addLoan(new MortgageLoan());
                break;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE,type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {

        Bank bank = banks.stream().filter(b->b.getName().equals(bankName)).findFirst().orElse(null);
        Loan loan = loanRepository.findFirst(loanType);
        if (loan == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND,loanType));
        }

//        if (loanType.equals("StudentLoan") && bank.getClass().getSimpleName().equals("CentralBank")){
//            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND,loanType));
//        }
        if (loanType.equals("StudentLoan") && !bank.getClass().getSimpleName().equals("BranchBank")){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND,loanType));
        }

        if (loanType.equals("AdultLoan") && !bank.getClass().getSimpleName().equals("CentralBank")){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND,loanType));
        }




        bank.addLoan(loan);
        loanRepository.removeLoan(loan);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {

        if (!(clientType.equals("Student")||clientType.equals("Adult"))){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }

        Bank bank = banks.stream().filter(b->b.getName().equals(bankName)).findFirst().orElse(null);


        Client client;
        switch (clientType){
            case "Student":
                client = new Student(clientName,clientID,income);
                break;
            case "Adult":
                client = new Adult(clientName, clientID, income);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }


        if (clientType.equals("Student") && bank.getClass().getSimpleName().equals("CentralBank")){
            return ConstantMessages.UNSUITABLE_BANK;
        }

        if (clientType.equals("Adult") && bank.getClass().getSimpleName().equals("BranchBank")){
            return ConstantMessages.UNSUITABLE_BANK;
        }

        bank.addClient(client);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK,clientType, bankName);
    }

    @Override
    public String finalCalculation(String bankName) {

        Bank bank = banks.stream().filter(b->b.getName().equals(bankName)).findFirst().orElse(null);
        Collection<Client> clients = bank.getClients();
        Collection<Loan> loans = bank.getLoans();
        double sumOfClientsIncome = clients.stream().mapToDouble(Client::getIncome).sum();
        double sumOfLoansAmounts = loans.stream().mapToDouble(Loan::getAmount).sum();
        double funds = sumOfLoansAmounts + sumOfClientsIncome;
        return String.format(ConstantMessages.FUNDS_BANK, bankName, funds);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        banks.forEach(b -> sb.append(b.getStatistics()).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
