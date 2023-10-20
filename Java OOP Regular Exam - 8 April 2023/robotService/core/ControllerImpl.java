package robotService.core;

import robotService.entities.robot.BaseRobot;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private final SupplementRepository supplementRepository;
    private final Collection<Service> services;

    public ControllerImpl() {
        this.services = new ArrayList<>();
        this.supplementRepository = new SupplementRepository();
    }

    public SupplementRepository getSupplementRepository() {
        return supplementRepository;
    }

    @Override
    public String addService(String type, String name) {

        Service service;

        switch (type) {
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        services.add(service);
        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, service.getClass().getSimpleName());

    }

    @Override
    public String addSupplement(String type) {

        Supplement supplement;

        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }


        supplementRepository.getSupplements().add(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, supplement.getClass().getSimpleName());
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {

        Service service = services.stream().filter(s -> s.getName().equals(serviceName)).findFirst().orElse(null);


        Supplement supplement = getSupplementRepository().getSupplements().stream()
                .filter(s -> s.getClass().getSimpleName().equals(supplementType))
                .findFirst().orElse(null);


        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        if (service == null) {
            throw new IllegalArgumentException(INVALID_SERVICE_TYPE);
        }

        service.addSupplement(supplement);
        getSupplementRepository().getSupplements().remove(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {


        Service service = services.stream().filter(s -> s.getName().equals(serviceName)).findFirst().orElse(null);

        if (service == null){
            throw new IllegalArgumentException(UNSUITABLE_SERVICE) ;
        }



        BaseRobot robot;
        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }


        String serviceType = service.getClass().getSimpleName();

        if (robotType.equals("MaleRobot") && !serviceType.equals("MainService")) {
            throw new IllegalArgumentException(UNSUITABLE_SERVICE);
        } else if (robotType.equals("FemaleRobot") && !serviceType.equals("SecondaryService")) {
            throw new IllegalArgumentException(UNSUITABLE_SERVICE);
        }

        service.addRobot(robot);

        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);


    }

    @Override
    public String feedingRobot(String serviceName) {

        Service service = services.stream().filter(s -> s.getName().equals(serviceName)).findFirst().orElse(null);

        if (service == null){
            throw new IllegalArgumentException(UNSUITABLE_SERVICE) ;
        }


        int count = 0;
        for (Robot robot : service.getRobots()) {
            robot.eating();
            count++;
        }

        return String.format(FEEDING_ROBOT, count);
    }

    @Override
    public String sumOfAll(String serviceName) {

        Service service = services.stream().filter(s -> s.getName().equals(serviceName)).findFirst().orElse(null);

        if (service == null){
            throw new IllegalArgumentException(UNSUITABLE_SERVICE) ;
        }

        double robotsPriceSum = 0.0;

        if (service.getRobots() != null) {
            for (Robot robot : service.getRobots()) {
                robotsPriceSum += robot.getPrice();
            }
        }

        double supplementsPriceSum = 0.0;

        if (service.getSupplements() != null) {

            for (Supplement supplement : service.getSupplements()) {
                supplementsPriceSum += supplement.getPrice();
            }
        }

        double totalSum = robotsPriceSum + supplementsPriceSum;

        return String.format(VALUE_SERVICE, serviceName, totalSum);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();

        services.forEach(s -> {
            sb.append(s.getStatistics());
        });

        return sb.toString().trim();
    }
}
