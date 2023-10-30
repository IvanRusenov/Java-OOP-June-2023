package gifts;

import org.junit.Assert;
import org.junit.Test;

public class GiftFactoryTests {

    @Test
    public void testGetCount() {
        GiftFactory giftFactory = new GiftFactory();
        Assert.assertEquals(0, giftFactory.getCount());
    }

    @Test
    public void testCreateGiftShouldCreate() {
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Gift", 32);
        giftFactory.createGift(gift);
        Assert.assertEquals(1, giftFactory.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftShouldThrow() {
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Gift", 32);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift);
    }

    @Test
    public void removeGiftShouldRemove() {
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Gift", 32);
        giftFactory.createGift(gift);
        Assert.assertTrue(giftFactory.removeGift("Gift"));
        Assert.assertEquals(0, giftFactory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void removeGiftShouldThrow() {
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Gift", 32);
        giftFactory.createGift(gift);
        giftFactory.removeGift("");

    }

    @Test
    public void testGetPresentWithLeastMagic(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Aift", 412.05);
        Gift gift1 = new Gift("Bift", 12);
        Gift gift2 = new Gift("Cift", 354);
        Gift gift3 = new Gift("Dift", 6352);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);
        giftFactory.createGift(gift3);

        Assert.assertEquals(12,giftFactory.getPresentWithLeastMagic().getMagic(),0.000001);
    }
    @Test
    public void testGetPresentShouldGet(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Aift", 412.05);
        giftFactory.createGift(gift);
        Assert.assertEquals("Aift",giftFactory.getPresent("Aift").getType());
    }

    @Test
    public void testGetPresents(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("Aift", 412.05);
        giftFactory.createGift(gift);


        Assert.assertEquals(1,giftFactory.getPresents().size());
    }
}
