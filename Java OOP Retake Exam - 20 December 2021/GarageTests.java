package garage;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GarageTests {

    @Test
    public void testConstructorShouldCreate() {
        Garage garage = new Garage();
        Assert.assertEquals(0, garage.getCount());
        Assert.assertTrue(garage.getCars().isEmpty());
    }

    @Test
    public void testAddCarShouldAdd() {
        Garage garage = new Garage();
        Car car = new Car("Ford", 200, 22.43);
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarShouldThrow() {
        Garage garage = new Garage();
        garage.addCar(null);
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        Garage garage = new Garage();
        Car car = new Car("Mazda", 200, 22.43);
        Car car1 = new Car("Trabant", 500, 22.43);
        Car car2 = new Car("Ford", 50, 22.43);
        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car);
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(60);
        Assert.assertEquals("Mazda", allCarsWithMaxSpeedAbove.get(0).getBrand());
        Assert.assertEquals(3, allCarsWithMaxSpeedAbove.size());
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        Garage garage = new Garage();
        Car car = new Car("Mazda", 200, 22.43);
        Car car1 = new Car("Trabant", 500, 22.44);
        Car car2 = new Car("Ford", 50, 22.45);
        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);

        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals("Ford", theMostExpensiveCar.getBrand());
        Assert.assertEquals(22.45, theMostExpensiveCar.getPrice(), 0.0001);
    }

    @Test
    public void testFindAllCarsByBrand() {
        Garage garage = new Garage();
        Car car = new Car("Mazda", 200, 22.43);
        Car car1 = new Car("Trabant", 500, 22.44);
        Car car3 = new Car("Trabant", 5100, 212.44);
        Car car2 = new Car("Ford", 50, 22.45);
        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        Assert.assertEquals(2, garage.findAllCarsByBrand("Trabant").size());

    }
}