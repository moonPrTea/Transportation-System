package models.Transport;

public class Ship extends Transport{

    public Ship(String model, String modelNumber, Double maxWeight, Double kmCost) {
        super(model, modelNumber, maxWeight, kmCost);
    }

    @Override
    public Double getTripCost(Double goodWeight, Double distance) {
        if (goodWeight > maxWeight) {
            throw new IllegalArgumentException("Goods weight is overload");
        }

        return kmCost * distance;
    }

    @Override
    public TransportType getType() {
        return TransportType.SHIP;
    }

    @Override
    public Double getAvgSpeed() { return TransportType.SHIP.getAvgSpeed(); }
}

