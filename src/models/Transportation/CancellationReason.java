package models.Transportation;

public enum CancellationReason {
    TRANSPORT_BREAKDOWN("Transport breakdown"),
    TRAFFIC_RESTRICTIONS("Traffic restrictions"),
    PROHIBITED_GOODS("Prohibited goods");

    private final String reasonTitle;

    CancellationReason(String reasonTitle) {
        this.reasonTitle = reasonTitle;
    }
}
