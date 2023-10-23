package magicGame;


import org.junit.Assert;
import org.junit.Test;

public class MagicianTests {

   @Test
    public void testConstructorShouldCreate(){
       Magician magician = new Magician("Ivan",200);
       Assert.assertEquals("Ivan",magician.getUsername());
       Assert.assertEquals(200, magician.getHealth());
       Assert.assertEquals(0,magician.getMagics().size());
   }

   @Test(expected = NullPointerException.class)
   public void testSetNameShouldThrow(){
      Magician magician = new Magician("",200);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testSetHealthShouldThrow(){
      Magician magician = new Magician("Ivan",-1);
   }

   @Test
   public void testTakeDamageShouldSetHealthTo0(){
      Magician magician = new Magician("Ivan",1);
      magician.takeDamage(100);
      Assert.assertEquals(0, magician.getHealth());
   }

   @Test
   public void testTakeDamageShouldSetHealth(){
      Magician magician = new Magician("Ivan",100);
      magician.takeDamage(50);
      Assert.assertEquals(50, magician.getHealth());
   }

   @Test(expected = IllegalStateException.class)
   public void testTakeDamageShouldThrow(){
      Magician magician = new Magician("Ivan",100);
      magician.takeDamage(100);
      magician.takeDamage(100);

   }

   @Test(expected = NullPointerException.class)
   public void testAddMagicShouldThrow(){
      Magician magician = new Magician("Ivan",100);
      Magic magic = null;
      magician.addMagic(magic);

   }

   @Test()
   public void testAddMagicShouldAdd(){
      Magician magician = new Magician("Ivan",100);
      Magic magic = new Magic("Magic",100);
      magician.addMagic(magic);

      Assert.assertEquals(1, magician.getMagics().size());
      Assert.assertEquals("Magic", magician.getMagic("Magic").getName());
      Assert.assertEquals(100, magician.getMagic("Magic").getBullets());

   }

   @Test
   public void testRemoveMagicShouldRemove(){
      Magician magician = new Magician("Ivan",100);
      Magic magic = new Magic("Magic",100);
      magician.addMagic(magic);
      magician.removeMagic(magic);

      Assert.assertEquals(0,magician.getMagics().size());
   }




}
