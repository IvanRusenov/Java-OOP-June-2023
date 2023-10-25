package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Test;

public class ExcavationTests {

    @Test
    public void testConstructorShouldCreate(){
        Excavation excavation = new Excavation("Plovdiv",20);
        Assert.assertEquals("Plovdiv", excavation.getName());
        Assert.assertEquals(20, excavation.getCapacity());
        Assert.assertEquals(0, excavation.getCount());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWithNegativeCapacity(){
        Excavation excavation = new Excavation("Plovdiv",-5);

    }

    @Test
    public void testSizeAndCapacity(){
        Excavation excavation = new Excavation("Sopot",20);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        excavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1,excavation.getCount());
        Assert.assertEquals(20,excavation.getCapacity());
    }

    @Test (expected = NullPointerException.class)
    public void testConstructorShouldThrowWithEmptyNmae(){
        Excavation excavation = new Excavation("",25);

    }

    @Test
    public void testAddArcheologistShouldAdd(){
        Excavation excavation = new Excavation("Sopot",20);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        excavation.addArchaeologist(archaeologist);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistShouldThrowWhenNoCapacity(){
        Excavation excavation = new Excavation("Sopot",1);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        Archaeologist archaeologist1 = new Archaeologist("Peter",1);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistShouldThrowWhenExist(){
        Excavation excavation = new Excavation("Sopot",20);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void testRemoveArchaeologistShouldRemove(){
        Excavation excavation = new Excavation("Sopot",20);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        excavation.addArchaeologist(archaeologist);
        Assert.assertTrue(excavation.removeArchaeologist(archaeologist.getName()));
    }

    @Test
    public void testRemoveArchaeologistShouldThrowWhenNoSuchName(){
        Excavation excavation = new Excavation("Sopot",20);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        excavation.addArchaeologist(archaeologist);
        Assert.assertFalse(excavation.removeArchaeologist("Peter"));
    }

    @Test
    public void testGetEnergy(){
        Excavation excavation = new Excavation("Sopot",20);
        Archaeologist archaeologist = new Archaeologist("Ivan",10);
        Assert.assertEquals(10, archaeologist.getEnergy(),0.0000001);
    }


}
