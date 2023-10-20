package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static robotService.common.ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseService implements Service {

    private final String name;
    private final int capacity;
    private final Collection<Supplement> supplements;
    private final Collection<Robot> robots;

    protected BaseService(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
    }

    @Override
    public Collection<Robot> getRobots() {
        return Collections.unmodifiableCollection(robots);
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(supplements);
    }

    @Override
    public void addRobot(Robot robot) {
        if (capacity == robots.size()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {
        return supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.getName(), this.getClass().getSimpleName())).append(System.lineSeparator());

        if (this.getRobots().size() > 0) {
            String robotsNames = this.getRobots().stream().map(Robot::getName).collect(Collectors.joining(" "));
            sb.append(String.format("Robots: %s", robotsNames)).append(System.lineSeparator());
        } else {
            sb.append("Robots: none").append(System.lineSeparator());
        }

        int count = this.getSupplements().size();
        int supplementsHardness = this.getSupplements().stream().mapToInt(Supplement::getHardness).sum();
        sb.append(String.format("Supplements: %d Hardness: %d", count, supplementsHardness)).append(System.lineSeparator());

        return sb.toString();
    }

}
