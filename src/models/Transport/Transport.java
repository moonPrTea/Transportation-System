package models.Transport;

public abstract class Transport {
    protected String model;
    protected String modelNumber;
    protected Double maxWeight;
    protected Double kmCost;

    public Transport(String model, String modelNumber, Double maxWeight, Double kmCost) {
        this.model = model;
        this.modelNumber = modelNumber;
        this.maxWeight = maxWeight;
        this.kmCost = kmCost;
    }

    public void transportInformation() {
        System.out.printf("{\n\tModel: %s\n\tModel number: %s\n\tMax weight: %.3f\n\tkm cost: %.3f\n}\n",
                model, modelNumber, maxWeight, kmCost);
    }

    public abstract Double getTripCost(Double kmCost, Double distance);
    public abstract TransportType getType();
}
