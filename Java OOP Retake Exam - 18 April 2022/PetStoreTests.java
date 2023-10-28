package petStore;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    @Test
    public void testConstructorShouldCreate(){
        PetStore petStore = new PetStore();
        Assert.assertEquals(0, petStore.getCount());
    }
    @Test
    public void testAddAnimalsShouldAdd(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Lion",100,23.56);
        petStore.addAnimal(animal);
        Assert.assertEquals(1, petStore.getAnimals().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalsShouldThrowWithNullAnimal(){
        PetStore petStore = new PetStore();
        Animal animal = null;
        petStore.addAnimal(animal);
    }

    @Test
    public void testFindAllAnimalBySpecieShouldFind(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Lion",100,23.56);
        petStore.addAnimal(animal);
        Assert.assertNotNull(petStore.findAllAnimalBySpecie("Lion"));

        List<Animal> lions = petStore.findAllAnimalBySpecie("Lion");
        Assert.assertEquals(1,lions.size());
        Assert.assertEquals("Lion",lions.get(0).getSpecie());

    }

    @Test
    public void testFindAllAnimalsWithMaxKilograms(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Lion",100,23.56);
        Animal animal1 = new Animal("Cat",5,230.56);
        Animal animal2 = new Animal("Chicken",1,3.56);
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);

        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(10);
        Assert.assertEquals(1,allAnimalsWithMaxKilograms.size());
        Assert.assertEquals(23.56, allAnimalsWithMaxKilograms.get(0).getPrice(),0.000001);
    }

    @Test
    public void testGetTheMostExpensiveAnimal(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Lion",100,23.56);
        Animal animal1 = new Animal("Cat",5,230.56);
        Animal animal2 = new Animal("Chicken",1,3.56);
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);

        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();

        Assert.assertEquals(230.56,theMostExpensiveAnimal.getPrice(),0.000000001);
    }



}

