package bank.entities.bank;

import bank.common.ExceptionMessages;
import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseBank implements Bank {


    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;


    public BaseBank(String name, int capacity) {
        setName(name);
        this.capacity = capacity;//todo check for setter
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }

        this.name = name;
    }
    //TODO CHECK IF COLLECTIONS MUST BE UNMODIFIABLE

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return this.loans;
    }

    @Override
    public void addClient(Client client) {
        //todo check logic
        if (getClients().size() < getCapacity()) {
            getClients().add(client);
        }else {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }


    }

    @Override
    public void removeClient(Client client) {
        getClients().remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        getLoans().add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        return  getLoans().stream().mapToInt(Loan::getInterestRate).sum();
    }

    @Override
    public String getStatistics() {

        //"Name: {bankName}, Type: {bankType}
        //Clients: {clientName1}, {clientName2} ... / Clients: none
        //Loans: {loansCount}, Sum of interest rates: {sumOfInterestRates}"

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s, Type: %s", this.getName(), this.getClass().getSimpleName())).append(System.lineSeparator());
        if (this.getClients().isEmpty()) {
            sb.append("Clients: none").append(System.lineSeparator());
        } else {
            sb.append(String.format("Clients: %s", this.getClients().stream().map(Client::getName).collect(Collectors.joining(", ")))).append(System.lineSeparator());
        }

        int sum = loans.stream().mapToInt(Loan::getInterestRate).sum();
        sb.append(String.format("Loans: %d, Sum of interest rates: %d", getLoans().size(), sumOfInterestRates())).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
