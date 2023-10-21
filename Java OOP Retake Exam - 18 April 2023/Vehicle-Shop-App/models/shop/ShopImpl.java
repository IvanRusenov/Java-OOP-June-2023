package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

import java.util.Collection;

public class ShopImpl implements Shop {
    @Override
    public void make(Vehicle vehicle, Worker worker) {

        Collection<Tool> tools = worker.getTools();

        while (!vehicle.reached() && worker.canWork() && tools.iterator().hasNext()) {
            Tool tool = tools.iterator().next();
            tool.decreasesPower();
            worker.working();
            vehicle.making();

            if (tool.isUnfit()) {
                tool = tools.iterator().next();
            }

        }

    }
}
