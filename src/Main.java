import models.Cargo.Cargo;
import models.Cargo.CargoType;
import models.Transport.*;
import models.Transportation.Transportation;
import repositories.TransportationRepository;

public class Main {
    public static void main(String[] args) {
        Double goodWeight = 134340.3;
        Double distance = 5000.12;

        Plane examplePlane = new Plane(
                "plane to delivery smth cool!",
                "back2016",
                1200000.8,
                3242.0);

        Train exampleTrain = new Train(
                "train to your funny memories",
                "trend train 007",
                2943920.22,
                2300.1);

        Ship exampleShip = new Ship(
                "Ship in production without bugs",
                "mask11",
                4742730.257,
                3800.0);

        printTransportInfo(examplePlane, goodWeight, distance);
        printTransportInfo(exampleTrain, goodWeight, distance);
        printTransportInfo(exampleShip, goodWeight, distance);

        exampleTrain.setCountCarriages(120);
        System.out.printf("Avg carriage weight: %.2f\n", exampleTrain.avgCarriagesWeight());

        Cargo firstCargo = new Cargo("Dangerous cargo within additional information",
                1200, 232323.01, CargoType.DANGEROUS);
        Cargo secondCargo = new Cargo("Lots of oil", 15000, 123242.32, CargoType.LIQUID);
        Cargo thirdCargo = new Cargo("Product with bags", 111111, 88888.8, CargoType.PERISHABLE);

        Transportation dangerousTransportation = new Transportation(1, firstCargo, examplePlane, distance, examplePlane.getTripCost(firstCargo.goodsWeight(), distance));
        Transportation oilTransportation = new Transportation(2, secondCargo, exampleShip, distance, exampleShip.getTripCost(secondCargo.goodsWeight(), distance));
        Transportation perishableTransportation = new Transportation(3, thirdCargo, exampleTrain, distance, exampleShip.getTripCost(thirdCargo.goodsWeight(), distance));

        dangerousTransportation.startProcess();
        dangerousTransportation.endProcess();
        oilTransportation.startProcess();

        System.out.printf("Transportation is available: %b\n", perishableTransportation.checkAvailableTransportation());

        TransportationRepository<Transportation> transportationRepository = new TransportationRepository<>();
        transportationRepository.RegisterTransportation();
        transportationRepository.addTransportation(perishableTransportation);
        transportationRepository.addTransportation(dangerousTransportation);
        transportationRepository.addTransportation(oilTransportation);

        System.out.println("All transportations:\n");
        for (int index = 0; index < transportationRepository.getSize(); index++) {
            System.out.println(transportationRepository.getTransportation(index));
        }

        System.out.printf("Total revenue: %.2f\n", transportationRepository.showTotalIncome());
        System.out.printf("Completed Transportations: %.2f\n", transportationRepository.countCompletedTransportations());
    }

    private static void printTransportInfo(Transport transport, Double weight, Double distance) {
        System.out.printf("Type: %s\n", transport.getType());
        transport.transportInformation();
        System.out.printf("Expected cost for transporting %.2f kg over %.2f km: %.2f\n\n",
                weight, distance, transport.getTripCost(weight, distance));
    }
}
