package models.Transport;

public class Train extends Transport {
    private Integer countCarriagies;

    public Train(String model, String modelNumber, Double maxWeight, Double kmCost) {
        super(model, modelNumber, maxWeight, kmCost);
    }

    @Override
    public Double getTripCost(Double goodWeight, Double distance) {
        if (goodWeight > maxWeight) {
            throw new IllegalArgumentException("Goods weight is overload");
        }

        double price = kmCost * distance;

        // train has discount (12%), when goods weight is more than 65% of max weight
        if (goodWeight > maxWeight * 0.65) {
            price *= 0.88;
        }

        return price;
    }

    @Override
    public TransportType getType() {
        return TransportType.TRAIN;
    }

    public Double avgCarriagiesWeigth() {
        return maxWeight / countCarriagies;
    }
}
