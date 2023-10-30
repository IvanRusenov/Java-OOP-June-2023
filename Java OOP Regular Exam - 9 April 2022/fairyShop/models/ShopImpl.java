package fairyShop.models;

import java.util.Collection;
import java.util.Collections;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {

        Collection<Instrument> instruments = helper.getInstruments();
        Instrument instrument = instruments.iterator().next();

        while (instruments.iterator().hasNext() && !present.isDone() && helper.canWork()) {

            if (instrument.isBroken()) {
                instrument = instruments.iterator().next();
            }

            instrument.use();
            present.getCrafted();
            helper.work();


        }

        //todo
    }
}
