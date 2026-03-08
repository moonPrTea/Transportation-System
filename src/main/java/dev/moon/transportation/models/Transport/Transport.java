package dev.moon.transportation.models.Transport;

import java.math.BigDecimal;

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

    public abstract Double getAvgSpeed();
    public abstract Double getTripCost(Double kmCost, Double distance);
    public abstract TransportType getType();
}
