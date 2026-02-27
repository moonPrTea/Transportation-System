package dev.moon.transportation.repositories;

import dev.moon.transportation.models.Transportation.Transportation;
import dev.moon.transportation.models.Transportation.TransportationStatus;


public class TransportationRepository<T extends Transportation> {
    private Repository<T> repository;

    public void RegisterTransportation() {
        this.repository = new Repository<>();
    }

    public void addTransportation(T transportation) {
        repository.addItem(transportation);
    }

    public T getTransportation(int index) {
        return repository.getItem(index);
    }

    public int getSize() {
        return repository.size();
    }

    public Double showTotalIncome() {
        double total = 0;

        for (int index = 0; index < repository.size(); index++ ) {
            T item = repository.getItem(index);
            if (item.getStatus() == TransportationStatus.COMPLETED) {
                total += item.getTransportationCost();
            }
        }

        return total;
    }

    public Double countCompletedTransportations() {
        double completedTransportations = 0;

        for (int index = 0; index < repository.size(); index++ ) {
            T item = repository.getItem(index);
            if (item.getStatus() == TransportationStatus.COMPLETED) {
                completedTransportations += 1;
            }
        }

        return completedTransportations;
    }
}
