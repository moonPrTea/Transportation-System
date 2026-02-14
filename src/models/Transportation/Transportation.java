package models.Transportation;

import models.Cargo.Cargo;
import models.Transport.Transport;

public class Transportation implements DeliveryProcess {
    private Integer id;
    private Cargo cargo;
    private Transport transport;
    private Double distance;
    private Double transportationCost;
    private TransportationStatus status;

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
        this.distance = distance;
        this.transportationCost = transportationCost;
        this.status = TransportationStatus.CREATED;
    }

    @Override
    public void startProcess() {
        if (status == TransportationStatus.COMPLETED) {
            throw new IllegalArgumentException("Can't start a completed transportation");
        }
        if (!checkAvailableTransportation()) {
            throw new IllegalArgumentException("Traffic is busy now, can't start transportation");
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
                "Cargo: %s, Transport: %s, Status: %s, Cost: %.2f",
                cargo.description(),
                transport.getType(),
                status,
                transportationCost
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

    // TODO: add method to cancel transportation and check the damages and losses
}
