package dev.moon.transportation.models.Cargo;

public record Cargo (
    String description,
    Integer goodsCount,
    Double goodsWeight,
    CargoType type
) {

    // compact constructor
    public Cargo {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description can't be empty");
        }

        if (goodsWeight == null || goodsWeight <= 0) {
            throw new IllegalArgumentException("Goods weight must be positive");
        }

        if (type == null) {
            throw new IllegalArgumentException("Cargo type can't be null");
        }
    }
}
