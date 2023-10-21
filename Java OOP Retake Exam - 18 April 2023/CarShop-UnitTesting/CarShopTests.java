package carShop;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CarShopTests {

    @Test (expected = NullPointerException.class)
    public void testAddShouldThrowWithNullName(){

        CarShop carShop = new CarShop();
        Car car = null;
        carShop.add(car);

    }

    @Test
    public void testAddShouldAdd(){
        Car car = new Car("Bentli",300,10000000.00);
        CarShop carShop = new CarShop();
        carShop.add(car);
        Assert.assertEquals(1, carShop.getCount());
        Assert.assertEquals("Bentli",carShop.getCars().get(0).getModel());
        Assert.assertEquals(300,carShop.getCars().get(0).getHorsePower());
        Assert.assertEquals(10000000.00,carShop.getCars().get(0).getPrice(),0.000000001);
    }

    @Test
    public void testFindAllCarsWithMaxHorsePowerShouldFined(){
        Car car = new Car("Bentli",100,100.00);
        Car car1 = new Car("Mazeratti",300,100.00);
        CarShop carShop = new CarShop();
        carShop.add(car);
        carShop.add(car1);
        List<Car> carsWithMaxHorsePower = carShop.findAllCarsWithMaxHorsePower(150);
        Assert.assertEquals(1, carsWithMaxHorsePower.size());
        Assert.assertEquals("Mazeratti", carsWithMaxHorsePower.get(0).getModel());
    }

    @Test
    public void testRemoveShouldRemove(){
        Car car = new Car("Bentli",100,100.00);
        Car car1 = new Car("Mazeratti",300,100.00);
        CarShop carShop = new CarShop();
        carShop.add(car);
        carShop.add(car1);


        Assert.assertTrue(carShop.remove(car));
        Assert.assertEquals(1, carShop.getCount());
    }

    @Test

    public void testGetTheMostLuxuryCarShouldReturn(){
        Car car = new Car("Bentli",100,100.00);
        Car car1 = new Car("Mazeratti",300,1000.00);
        Car car2 = new Car("Ferrarri",30,5000.00);
        CarShop carShop = new CarShop();
        carShop.add(car);
        carShop.add(car1);
        carShop.add(car2);

        Car theMostLuxuryCar = carShop.getTheMostLuxuryCar();
        Assert.assertEquals("Ferrarri", theMostLuxuryCar.getModel());

    }

    @Test
    public void testFindAllCarByModelSouldReturn(){
        Car car3 = new Car("Bentli",100,100.00);
        Car car = new Car("Bentli",200,200.00);
        Car car1 = new Car("Mazeratti",300,1000.00);
        Car car2 = new Car("Ferrarri",30,5000.00);
        CarShop carShop = new CarShop();
        carShop.add(car);
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);

        List<Car> allCarByModel = carShop.findAllCarByModel("Bentli");

        Assert.assertEquals(2, allCarByModel.size());
        Assert.assertEquals("Bentli", allCarByModel.get(0).getModel());
    }

    @Test
    public void testListCarCarsShouldExist(){
        CarShop carShop = new CarShop();
        Assert.assertEquals(0,carShop.getCount());
    }

}

