package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {

    @Test
    public void testConstructorShouldCreate(){
        House house = new House("Home",1);
        Assert.assertEquals(1, house.getCapacity());
    }
    @Test
    public void testGetName(){
        House house = new House("Home",1);
        Assert.assertEquals("Home", house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrow(){
        House house = new House("    ",1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrow(){
        House house = new House("Home",-1);
    }

    @Test
    public void testGetCount(){
        House house = new House("Home",10);
        Cat cat = new Cat("Adi");
        house.addCat(cat);
        house.addCat(cat);
        Assert.assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatShouldThrowWhenNoCapacity(){
        House house = new House("Home",1);
        Cat cat = new Cat("Adi");
        house.addCat(cat);
        house.addCat(cat);
    }

    @Test
    public void testRemoveCatShouldRemove(){
        House house = new House("Home",1);
        house.addCat(new Cat("Adi"));
        house.removeCat("Adi");
        Assert.assertEquals(0,house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatShouldThrow(){
        House house = new House("Home",10);
        house.addCat(new Cat("Adi"));
        house.removeCat("Bety");
    }

    @Test
    public void testCatForSale(){
        House house = new House("Home",1);
        Cat cat = new Cat("Adi");
        house.addCat(cat);
        house.catForSale("Adi");
        Assert.assertFalse(cat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleShouldThrow(){
        House house = new House("Home",1);
        Cat cat = new Cat("Adi");
        house.addCat(cat);
        house.catForSale("Bety");
    }

    @Test
    public void testStatistics(){
        House house = new House("Home",1);
        Cat cat = new Cat("Adi");
        house.addCat(cat);
        Assert.assertEquals("The cat Adi is in the house Home!", house.statistics());
    }


}
