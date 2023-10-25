package farmville;

import org.junit.Assert;
import org.junit.Test;

public class FarmvilleTests {

    @Test
    public void testConstructorShouldCreate(){
        Farm farm = new Farm("Farm",10);
        Assert.assertEquals("Farm", farm.getName());
        Assert.assertEquals(0,farm.getCount());
        Assert.assertEquals(10,farm.getCapacity());
    }

    @Test
    public void testAddShouldAdd(){
        Farm farm = new Farm("Farm",10);
        Animal animal = new Animal("Cow",200);
        farm.add(animal);

        Assert.assertEquals("Cow",animal.getType());
        Assert.assertEquals(1,farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowNoCapacity(){
        Farm farm = new Farm("Farm",1);
        Animal animal = new Animal("Cow",200);
        Animal animal1 = new Animal("Lion",200);
        farm.add(animal);
        farm.add(animal1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowAnimalExist(){
        Farm farm = new Farm("Farm",100);
        Animal animal = new Animal("Cow",200);
        farm.add(animal);
        farm.add(animal);
    }

    @Test
    public void testRemoveShouldReturnTrue(){
        Farm farm = new Farm("Farm",100);
        Animal animal = new Animal("Cow",200);
        farm.add(animal);
        Assert.assertTrue(farm.remove(animal.getType()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowWithZero(){
        Farm farm = new Farm("Farm",-1);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWithEmptyName(){
        Farm farm = new Farm("     ",100);
    }





    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Farm
}
