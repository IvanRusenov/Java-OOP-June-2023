package toyStore;


import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ToyStoryTest {

    @Test
    public void testGetToyShelfShouldReturnCollection() {
        ToyStore toyStore = new ToyStore();
        Map<String, Toy> toyShelf = toyStore.getToyShelf();

        Assert.assertTrue("A", toyShelf.containsKey("A"));
        Assert.assertTrue("B", toyShelf.containsKey("B"));
        Assert.assertTrue("C", toyShelf.containsKey("C"));
        Assert.assertTrue("D", toyShelf.containsKey("D"));
        Assert.assertTrue("E", toyShelf.containsKey("E"));
        Assert.assertTrue("F", toyShelf.containsKey("F"));
        Assert.assertTrue("G", toyShelf.containsKey("G"));
        Assert.assertFalse("H", toyShelf.containsKey("H"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrow() throws OperationNotSupportedException {
        ToyStore toyStore = new ToyStore();
        Map<String, Toy> toyShelf = toyStore.getToyShelf();
        Toy toy = new Toy("Barbie", "15");

        toyStore.addToy("Z", toy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowWhenShelfIsTaken() throws OperationNotSupportedException {
        ToyStore toyStore = new ToyStore();
        Toy toy = new Toy("A", "15");
        Toy toy1 = new Toy("A", "116");

        toyStore.addToy("A", toy);
        toyStore.addToy("A", toy1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyShouldThrowWhenShelfContainsToy() throws OperationNotSupportedException {

        ToyStore toyStore = new ToyStore();
        Toy toy = new Toy("A", "15");
        Toy toy1 = new Toy("A", "116");

        toyStore.addToy("A", toy);
        toyStore.addToy("B", toy);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowWhenShelfDontExist() throws OperationNotSupportedException {
        ToyStore toyStore = new ToyStore();
        Toy toy = new Toy("A", "15");
        toyStore.removeToy("Z", toy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowWhenToyIsDifferent() throws OperationNotSupportedException {
        ToyStore toyStore = new ToyStore();
        Toy toy = new Toy("Ken", "15");
        Toy toy1 = new Toy("Barbie", "15");
        toyStore.addToy("B", toy1);
        toyStore.removeToy("A", toy1);
    }

    @Test
    public void testRemoveToyShouldRemove() throws OperationNotSupportedException {
        ToyStore toyStore = new ToyStore();
        Toy toy = new Toy("Ken", "15");
        toyStore.addToy("B", toy);
        toyStore.removeToy("B", toy);
        Assert.assertNull(toyStore.getToyShelf().get("B"));
    }

    @Test
    public void testRemoveToyShouldRemoveAndReturnString() throws OperationNotSupportedException {
        ToyStore toyStore = new ToyStore();
        Toy toy = new Toy("Ken", "15");
        toyStore.addToy("B", toy);
        Assert.assertEquals("Remove toy:15 successfully!", toyStore.removeToy("B", toy));
    }


}