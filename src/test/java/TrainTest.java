import dev.moon.transportation.models.Transport.Train;
import dev.moon.transportation.models.Transport.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainTest {

    public Train train;

    @BeforeEach
    void setUp() {
        train = new Train("Example Model", "Num123", 100000.00, 1400.00);
    }

    // TODO: add assertions for exception messages
    @ParameterizedTest
    @CsvSource({
            "111111.00, 1.00",
            "12, -1.00"
    })
    void shouldThrowExceptionIfWeightIsMoreThanMaxWeight(double goodWeight, double distance) {
        assertThrows(IllegalArgumentException.class, () -> train.getTripCost(goodWeight, distance));
    }

    @ParameterizedTest
    @CsvSource({
            "1503.00, 12430.00, 17402000.00",
            "0.00, 0.00, 0.00",
            "0.00, 1.00, 1400.00",
            "1.00, 0.00, 0.00",
            "70000.00, 10.0, 12320.0"
    })
    void testGetTripCostSuccessfully(double goodWeight, double distance, double expectedResult) {
        assertEquals(expectedResult, train.getTripCost(goodWeight, distance));
    }

    @Test
    void testGetTransportTypeSuccessfully() {
        assertEquals(TransportType.TRAIN, train.getType());
    }

    @Test
    void testGetAvgTrainSpeedSuccessfully() {
        assertEquals(80.00, train.getAvgSpeed());
    }

    @ParameterizedTest
    @CsvSource({
            "100, 100",
            "-1, 1"
    })
    void shouldAddCountCarriagesToTrain(int countCarriages, int expectedCountCarriages) {
        train.setCountCarriages(countCarriages);
        assertEquals(expectedCountCarriages, train.getCountCarriages());
    }

    @Test
    void testGetAvgCarriagesWeightSuccessfully() {
        train.setCountCarriages(100);
        assertEquals(1000.0, train.avgCarriagesWeight());
    }
}
