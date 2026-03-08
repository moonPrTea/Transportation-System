import dev.moon.transportation.models.Cargo.Cargo;
import dev.moon.transportation.models.Cargo.CargoType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CargoTest {

    @ParameterizedTest
    @CsvSource({
            " , 12, 4343.00, Description can't be empty",
            "some description, -1, 4343.00, Goods count must be positive",
            "some description, 33, -1.00, Goods weight must be positive",
    })
    void shouldThrowExceptionIfFieldsIsNull(String description, Integer goodsCount, Double goodsWeight, String expectedMessage) {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Cargo(description, goodsCount, goodsWeight, CargoType.SOLID));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
