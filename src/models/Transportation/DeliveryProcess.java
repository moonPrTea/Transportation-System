package models.Transportation;

public interface DeliveryProcess {
    void startProcess();
    boolean checkAvailableTransportation(); // if true -> the process can be started
    void endProcess();
}
