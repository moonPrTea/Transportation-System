package models.Transport;

public class Plane extends Transport{

    // initializes transport fields
    public Plane(String model, String modelNumber, Double maxWeight, Double kmCost) {
        super(model, modelNumber, maxWeight, kmCost);
    }

    @Override
    public Double getTripCost(Double goodWeight, Double distance) {
        if (goodWeight > maxWeight) {
            throw new IllegalArgumentException("Goods weight is overload");
        }

        return 2 * kmCost * distance;
    }

    @Override
    public TransportType getType() {
        return TransportType.PLANE;
    }

    @Override
    public Double getAvgSpeed() { return TransportType.PLANE.getAvgSpeed(); }
}
