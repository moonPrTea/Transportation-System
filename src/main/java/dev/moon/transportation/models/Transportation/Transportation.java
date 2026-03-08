package dev.moon.transportation.models.Transportation;



import dev.moon.transportation.models.Cargo.Cargo;
import dev.moon.transportation.models.Transport.Transport;

import java.util.Random;

public class Transportation implements DeliveryProcess {
    private Integer id;
    private Cargo cargo;
    private Transport transport;
    private Double transportationCost;
    private TransportationStatus status;
    private CancellationReason canceledReason;

    public Transportation(Integer id, Cargo cargo, Transport transport, Double distance, Double transportationCost) {
        if (distance == null || distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        this.id = id;
        this.cargo = cargo;
        this.transport = transport;
        this.transportationCost = transportationCost;
        this.status = TransportationStatus.CREATED;
    }

    @Override
    public void startProcess() {
        if (status == TransportationStatus.COMPLETED) {
            throw new IllegalArgumentException("Can't start a completed transportation");
        }
        if (!checkAvailableTransportation()) {
            throw new IllegalArgumentException("Transportation is already in process");
        }

        status = TransportationStatus.PROCESSING;
    }

    @Override
    public boolean checkAvailableTransportation() {
        return status == TransportationStatus.CREATED;
    }

    @Override
    public void endProcess() {
        if (status == TransportationStatus.COMPLETED) {
            throw new IllegalArgumentException("Transportation has already been completed");
        }

        if (status == TransportationStatus.CREATED) {
            throw new IllegalArgumentException("Can't skip the status of transportation completion");
        }

        status = TransportationStatus.COMPLETED;
    }

    @Override
    public String toString() {
        return String.format(
                "Cargo: %s, Transport: %s, Status: %s, Cost: %.2f, Cancellation reason: %s",
                cargo.description(),
                transport.getType(),
                status,
                transportationCost,
                canceledReason != null ? canceledReason: "not found"
        );
    }

    public Integer getId() {
        return id;
    }

    public Double getTransportationCost() {
        return transportationCost;
    }

    public TransportationStatus getStatus() {
        return status;
    }

    public void setStatus(TransportationStatus status) {
        this.status = status;
    }

    public void cancelTransportation() {
        this.status = TransportationStatus.CANCELED;

        setRandomCancellationReason();
    }

    public void setRandomCancellationReason() {
        Random random = new Random();

        CancellationReason[] reasons = CancellationReason.values();
        this.canceledReason = reasons[random.nextInt(reasons.length)];
    }

    public CancellationReason getCancellationReason() {
        return this.canceledReason;
    }
}
