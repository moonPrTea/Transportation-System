import dev.moon.transportation.models.Transport.Plane;
import dev.moon.transportation.models.Transport.Ship;
import dev.moon.transportation.models.Transport.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShipTest {
    Ship ship;

    @BeforeEach
    void setUpPlanes() {
        ship = new Ship("Example model", "model number11", 12332332.32, 1848.33);
    }

    @ParameterizedTest
    @CsvSource({
            "43844848484.00, 1.00, Goods weight is overload",
            "12, -21321.00, Distance must be positive"
    })
    void shouldThrowExceptionIfWeightIsMoreThanMaxWeight(double goodWeight, double distance, String expectedMessage) {
        var exception = assertThrows(IllegalArgumentException.class, () -> ship.getTripCost(goodWeight, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "123433.00, 122.00, 225496.25",
            "0.00, 0.00, 0.00",
            "0.00, 1.00, 1848.32",
            "1.00, 0.00, 0.00",
    })
    void testGetTripCostSuccessfully(double goodWeight, double distance, double expectedResult) {
        assertEquals(expectedResult, ship.getTripCost(goodWeight, distance));
    }

    @Test
    void testGetTransportTypeSuccessfully() {
        assertEquals(TransportType.SHIP, ship.getType());
    }

    @Test
    void testGetAvgTrainSpeedSuccessfully() {
        assertEquals(40.0, ship.getAvgSpeed());
    }
}
