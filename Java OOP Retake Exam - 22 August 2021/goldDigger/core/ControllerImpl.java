package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{

    Repository<Discoverer> discovererRepository;
    Repository<Spot> spotRepository;

    Operation operation;

    private int inspectedSpotsCount;


    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.operation = new OperationImpl();
        inspectedSpotsCount = 0;
        //TODO
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {

        Discoverer discoverer;

        switch (kind){
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }

        discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED,kind,discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {

        Spot spot = new SpotImpl(spotName);
        spot.getExhibits().addAll(Arrays.asList(exhibits));
        spotRepository.add(spot);

        return String.format(ConstantMessages.SPOT_ADDED,spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST,discovererName));
        }

        discovererRepository.remove(discoverer);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE,discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        inspectedSpotsCount++;
        Spot spot = spotRepository.byName(spotName);
        List<Discoverer> suitableDiscoverers = discovererRepository
                .getCollection().stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());

        if (suitableDiscoverers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        operation.startOperation(spot,suitableDiscoverers);
        long excludedDiscoverers = suitableDiscoverers.stream().filter(d->d.getEnergy()==0).count();

        return String.format(ConstantMessages.INSPECT_SPOT,spotName,excludedDiscoverers);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT,inspectedSpotsCount)).append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        discovererRepository.getCollection().forEach(d -> sb.append(d.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
