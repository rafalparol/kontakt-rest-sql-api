package inc.temp.right.always.restsqlapi;

import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import inc.temp.right.always.restsqlapi.services.AnomaliesService;
import inc.temp.right.always.restsqlapi.services.custom.CustomAnomaliesPreparedStatementSetter;
import inc.temp.right.always.restsqlapi.services.custom.CustomAnomaliesRowMapper;
import inc.temp.right.always.restsqlapi.util.DateAndTimeConversionManager;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DateAndTimeConversionManagerTests {
    @Test
    public void fromLocalDateToTimestamp_Test() {
        DateAndTimeConversionManager dateAndTimeConversionManager = new DateAndTimeConversionManager();

        Timestamp expectedResult = Timestamp.valueOf("2024-02-01 00:00:00");
        Timestamp returnedResult = dateAndTimeConversionManager.fromStringDateToTimestamp("2024-02-01");

        assertEquals(expectedResult, returnedResult, String.format("Expected result: %s and returned result: %s are not the same when querying thermometers with anomalies.", expectedResult, returnedResult));
    }
}
