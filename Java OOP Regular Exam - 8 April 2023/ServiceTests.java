package robots;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ServiceTests {

    @Test
    public void testConstructorShouldCreate() {
        Service service = new Service("Ivan", 10);
        Assert.assertEquals("Ivan", service.getName());
        Assert.assertEquals(10, service.getCapacity());
        Assert.assertEquals(0, service.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWhenNameIsEmpty() {
        Service service = new Service("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWenCapacityIsNegative() {
        Service service = new Service("Ivan", -5);
    }

    @Test
    public void testAddShouldAdd() {
        Service service = new Service("Ivan", 10);
        Robot robot = new Robot("Ivan");
        service.add(robot);
        Assert.assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowWhenNoCapacity() {
        Service service = new Service("Ivan", 1);
        Robot robot = new Robot("Ivan");
        service.add(robot);
        service.add(robot);
    }

    @Test
    public void testRemoveShouldRemove() {
        Service service = new Service("Ivan", 1);
        Robot robot = new Robot("Ivan");
        service.add(robot);
        service.remove(robot.getName());
        Assert.assertEquals(0, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowWhenWrongNameGiven() {
        Service service = new Service("Ivan", 1);
        Robot robot = new Robot("Ivan");
        service.add(robot);
        service.remove("Pesho");
    }

    @Test
    public void testForSale() {
        Service service = new Service("Ivan", 1);
        Robot robot = new Robot("Ivan");
        service.add(robot);
        service.forSale("Ivan");
        Assert.assertFalse(robot.isReadyForSale());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testForSaleShouldThrowWhenWrongRobotNameIsGiven() {
        Service service = new Service("Ivan", 1);
        service.forSale("Ivan");
    }

    @Test
    public void testReportShouldReturnReport(){
        Service service = new Service("Ivan", 2);
        Robot robot = new Robot("Ivan");
        service.add(robot);
        service.add(robot);
        Assert.assertEquals("The robot Ivan, Ivan is in the service Ivan!",service.report());
    }


}
