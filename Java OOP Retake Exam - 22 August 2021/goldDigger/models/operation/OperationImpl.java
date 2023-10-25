package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation {

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig() && !spot.getExhibits().isEmpty()){
                discoverer.dig();
                String treasure = spot.getExhibits().iterator().next();
                discoverer.getMuseum().getExhibits().add(treasure);
                spot.getExhibits().remove(treasure);
            }
        }
    }
}
