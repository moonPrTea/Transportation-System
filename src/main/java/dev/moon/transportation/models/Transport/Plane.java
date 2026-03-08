package dev.moon.transportation.models.Transport;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        if (distance < 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }

        return new BigDecimal(kmCost)
                .multiply(BigDecimal.valueOf(distance))
                .multiply(BigDecimal.valueOf(2))
                .setScale(2, RoundingMode.FLOOR)
                .doubleValue();
    }

    @Override
    public TransportType getType() {
        return TransportType.PLANE;
    }

    @Override
    public Double getAvgSpeed() { return TransportType.PLANE.getAvgSpeed(); }
}
