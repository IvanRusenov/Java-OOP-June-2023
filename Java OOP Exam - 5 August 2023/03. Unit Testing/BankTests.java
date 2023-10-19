package bank;

import org.junit.Assert;
import org.junit.Test;

public class BankTests {

    @Test
    public void testConstructorShouldCrete() {
        Bank bank = new Bank("Post", 10);
        Assert.assertEquals("Post", bank.getName());
        Assert.assertEquals(10, bank.getCapacity());
        Assert.assertEquals(0, bank.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrow() {
        Bank bank = new Bank("    ", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrow() {
        Bank bank = new Bank("Post", -20);
    }

    @Test
    public void testAddClientShouldAdd() {
        Bank bank = new Bank("Post", 10);
        Client client = new Client("Ivan");
        bank.addClient(client);
        Assert.assertEquals(1,bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientShouldThrow() {
        Bank bank = new Bank("Post", 1);
        Client client = new Client("Ivan");
        bank.addClient(client);
        bank.addClient(client);
    }

    @Test
    public  void testRemoveClientSholdRemove(){
        Bank bank = new Bank("Post", 1);
        Client client = new Client("Ivan");
        bank.addClient(client);
        bank.removeClient("Ivan");
        Assert.assertEquals(0,bank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveClientSholdThrow(){
        Bank bank = new Bank("Post", 1);
        Client client = new Client("Ivan");
        bank.addClient(client);
        bank.removeClient("Pesho");
    }

    @Test
    public void testLoanWithdrawalShouldWithdraw(){
        Bank bank = new Bank("Post", 1);
        Client client = new Client("Ivan");
        bank.addClient(client);
        bank.loanWithdrawal("Ivan");
        Assert.assertFalse(client.isApprovedForLoan());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawalShouldThrowWithNoSuchName(){
        Bank bank = new Bank("Post", 1);
        Client client = new Client("Ivan");
        bank.addClient(client);
        bank.loanWithdrawal("Pesho");
    }

    @Test
    public void testStatisticsShouldGetStatistics(){
        Bank bank = new Bank("Post", 10);
        Client client = new Client("Ivan");
        Client client1 = new Client("Pesho");
        bank.addClient(client);
        bank.addClient(client1);
        Assert.assertEquals( "The client Ivan, Pesho is at the Post bank!",bank.statistics());
    }
}
