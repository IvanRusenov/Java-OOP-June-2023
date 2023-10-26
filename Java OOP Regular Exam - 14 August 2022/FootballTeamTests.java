package football;

import org.junit.Assert;
import org.junit.Test;

public class FootballTeamTests {

    @Test
    public void testConstructorShouldCreate(){
        FootballTeam footballTeam = new FootballTeam("Botev",10);

        Assert.assertEquals("Botev", footballTeam.getName());
        Assert.assertEquals(10, footballTeam.getVacantPositions());
        Assert.assertEquals(0, footballTeam.getCount());

    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWithNullName(){
        FootballTeam footballTeam = new FootballTeam("",10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetVacantPositionsShouldThrowWithNegative(){
        FootballTeam footballTeam = new FootballTeam("Botev",-10);
    }

    @Test
    public void testAddFootballerShouldAdd(){
        FootballTeam footballTeam = new FootballTeam("Botev",1);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1,footballTeam.getCount());
        Assert.assertEquals(1,footballTeam.getVacantPositions());

    }

    @Test
    public void testRemoveFootballerShouldRemove(){
        FootballTeam footballTeam = new FootballTeam("Botev",1);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1, footballTeam.getCount());
        footballTeam.removeFootballer(footballer.getName());
        Assert.assertEquals(0, footballTeam.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballerShouldThrowWithInvalidName(){
        FootballTeam footballTeam = new FootballTeam("Botev",1);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.removeFootballer("Pesho");
    }

    @Test
    public void testFootballerForSaleShouldChangeIsActive(){
        FootballTeam footballTeam = new FootballTeam("Botev",1);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(footballer);
        footballTeam.footballerForSale("Ivan");
        Assert.assertFalse(footballer.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleShouldThrow(){
        FootballTeam footballTeam = new FootballTeam("Botev",1);
        footballTeam.footballerForSale("Pesho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerWhenNoCapacity(){
        FootballTeam footballTeam = new FootballTeam("Botev",1);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer);
    }

    @Test
    public void testGetStatisticsShouldGet(){
        FootballTeam footballTeam = new FootballTeam("Botev",2);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer);
        Assert.assertEquals("The footballer Ivan, Ivan is in the team Botev.", footballTeam.getStatistics());


    }

}
