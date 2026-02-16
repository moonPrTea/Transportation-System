package models.Cargo;

public enum CargoType {
    DANGEROUS("dangerous cargo"),
    SOLID("solid cargo"),
    LIQUID("liquid cargo"),
    GASEOUS("gaseous cargo"),
    PERISHABLE("perishable cargo");

    private final String cargoType;

    CargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    // risk coefficient
    public Double getRisks() {
        return switch (this) {
            case DANGEROUS -> 2.0;
            case SOLID -> 1.0;
            case LIQUID -> 1.3;
            case GASEOUS -> 1.5;
            case PERISHABLE -> 1.55;
        };
    }
}
