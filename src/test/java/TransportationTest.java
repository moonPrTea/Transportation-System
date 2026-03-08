import dev.moon.transportation.models.Cargo.Cargo;
import dev.moon.transportation.models.Cargo.CargoType;
import dev.moon.transportation.models.Transport.Train;
import dev.moon.transportation.models.Transportation.CancellationReason;
import dev.moon.transportation.models.Transportation.Transportation;
import dev.moon.transportation.models.Transportation.TransportationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TransportationTest {
    Transportation transportation;

    Train exampleTrain = new Train("model Mode", "134num", 3433423.00, 1111.9);
    Cargo exampleCargo = new Cargo("one example train cargo", 3434332, 1343443.43, CargoType.DANGEROUS);

    @BeforeEach
    void setUpTransportation() {
        transportation = new Transportation(1, exampleCargo, exampleTrain, 13434.12, exampleTrain.getTripCost(1343443.43, 13434.12));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 12.12, Id must be positive",
            ", 12.12, Id must be positive",
            "0, 12.12, Id must be positive",
            "2, -1.0, Distance must be positive",
            "2,, Distance must be positive",
            "2, 0, Distance must be positive",
    })
    void checkTransportation(Integer id, Double distance, String expectedMessage) {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Transportation(id, exampleCargo, exampleTrain, distance, exampleTrain.getTripCost(1343443.43, 13434.12)));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTransportationStatusIsCompleted() {
        transportation.setStatus(TransportationStatus.COMPLETED);
        var exception = assertThrows(IllegalArgumentException.class, () -> transportation.startProcess());
        assertEquals("Can't start a completed transportation", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTransportationIsNotAvailable() {
        transportation.setStatus(TransportationStatus.CANCELED);
        var exception = assertThrows(IllegalArgumentException.class, () -> transportation.startProcess());
        assertEquals("Transportation is already in process", exception.getMessage());
    }

    @Test
    void testStartTransportationProcessSuccessfully() {
        transportation.startProcess();
        assertEquals(TransportationStatus.PROCESSING, transportation.getStatus());
    }

    @Test
    void testCheckAvailableTransportation() {
        assertTrue(transportation.checkAvailableTransportation());
    }

    @ParameterizedTest
    @MethodSource("prepareStatusesAndExceptionMessages")
    void testThrowExceptionsInEndTransportationProcess(TransportationStatus newStatus, String expectedMessage) {
        transportation.setStatus(newStatus);
        var exception = assertThrows(IllegalArgumentException.class, () -> transportation.endProcess());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testEndTransportationProcessSuccessfully() {
        transportation.setStatus(TransportationStatus.PROCESSING);
        transportation.endProcess();
        assertEquals(TransportationStatus.COMPLETED, transportation.getStatus());
    }

    @Test
    void testGetTransportationCost() {
        assertEquals(1.493739802E7, transportation.getTransportationCost());
    }

    @Test
    void shouldCancelTransportation() {
        transportation.cancelTransportation();
        assertEquals(TransportationStatus.CANCELED, transportation.getStatus());
    }

    @Test
    void shouldSetRandomCancellationReason() {
        transportation.setRandomCancellationReason();

        CancellationReason cancellationReason = transportation.getCancellationReason();
        assertNotNull(cancellationReason);
        assertTrue(Arrays.asList(CancellationReason.values()).contains(cancellationReason));
    }

    @Test
    void testGetIdSuccessfully() {
        assertEquals(1, transportation.getId());
    }

    @Test
    void testTransportationFormatString() {
        assertEquals("Cargo: one example train cargo, Transport: TRAIN, Status: CREATED, Cost: 14937398,02, Cancellation reason: not found", transportation.toString());
    }

    static Stream<Arguments> prepareStatusesAndExceptionMessages() {
        return Stream.of(
                Arguments.of(TransportationStatus.COMPLETED, "Transportation has already been completed"),
                Arguments.of(TransportationStatus.CREATED, "Can't skip the status of transportation completion")
        );
    }
}
