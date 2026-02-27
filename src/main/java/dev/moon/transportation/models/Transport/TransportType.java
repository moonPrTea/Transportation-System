package dev.moon.transportation.models.Transport;

public enum TransportType {
    PLANE,
    SHIP,
    TRAIN;

    public Double getAvgSpeed() {
        return switch (this) {
            case TRAIN -> 80.00;
            case SHIP -> 40.0;
            case PLANE -> 800.0;
            default -> throw new IllegalStateException();
        };
    }
}
