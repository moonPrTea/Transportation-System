import dev.moon.transportation.models.Transport.Plane;
import dev.moon.transportation.models.Transport.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaneTest {
    Plane plane;

    @BeforeEach
    void setUpPlanes() {
        plane = new Plane("Example model", "model number11", 1222434.00, 1600.00);
    }

    @ParameterizedTest
    @CsvSource({
            "8484484.00, 1.00, Goods weight is overload",
            "12, -21321.00, Distance must be positive"
    })
    void shouldThrowExceptionIfWeightIsMoreThanMaxWeight(double goodWeight, double distance, String expectedMessage) {
        var exception = assertThrows(IllegalArgumentException.class, () -> plane.getTripCost(goodWeight, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "1503.00, 123.00, 393600.0",
            "0.00, 0.00, 0.00",
            "0.00, 1.00, 3200.0",
            "1.00, 0.00, 0.00",
    })
    void testGetTripCostSuccessfully(double goodWeight, double distance, double expectedResult) {
        assertEquals(expectedResult, plane.getTripCost(goodWeight, distance));
    }

    @Test
    void testGetTransportTypeSuccessfully() {
        assertEquals(TransportType.PLANE, plane.getType());
    }

    @Test
    void testGetAvgTrainSpeedSuccessfully() {
        assertEquals(800.0, plane.getAvgSpeed());
    }
}
