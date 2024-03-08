package inc.temp.right.always.restsqlapi;

import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import inc.temp.right.always.restsqlapi.services.AnomaliesService;
import inc.temp.right.always.restsqlapi.services.custom.CustomAnomaliesPreparedStatementSetter;
import inc.temp.right.always.restsqlapi.services.custom.CustomAnomaliesRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AnomaliesServiceTests {
    @Test
    public void findAllWithAnomaliesCountBiggerThan_NonEmptyList_Test() {
        AnomaliesService anomaliesService = new AnomaliesService();

        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        mockedResult.add(new AnomaliesCount("thermometer-1", 5));
        mockedResult.add(new AnomaliesCount("thermometer-2", 2));
        when(jdbcTemplate.query(AnomaliesService.QUERY,
            new CustomAnomaliesPreparedStatementSetter(1), new CustomAnomaliesRowMapper()
        )).thenReturn(mockedResult);

        anomaliesService.setJdbcTemplate(jdbcTemplate);

        List<AnomaliesCount> returnedResult = anomaliesService.findAllWithAnomaliesCountBiggerThan(1);

        verify(jdbcTemplate, times(1)).query(AnomaliesService.QUERY,
            new CustomAnomaliesPreparedStatementSetter(1), new CustomAnomaliesRowMapper()
        );

        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when querying thermometers with anomalies.", mockedResult, returnedResult));
    }

    @Test
    public void findAllWithAnomaliesCountBiggerThan_EmptyList_Test() {
        AnomaliesService anomaliesService = new AnomaliesService();

        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        when(jdbcTemplate.query(AnomaliesService.QUERY,
            new CustomAnomaliesPreparedStatementSetter(10), new CustomAnomaliesRowMapper()
        )).thenReturn(mockedResult);

        anomaliesService.setJdbcTemplate(jdbcTemplate);

        List<AnomaliesCount> returnedResult = anomaliesService.findAllWithAnomaliesCountBiggerThan(10);

        verify(jdbcTemplate, times(1)).query(AnomaliesService.QUERY,
            new CustomAnomaliesPreparedStatementSetter(10), new CustomAnomaliesRowMapper()
        );

        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when querying thermometers with anomalies.", mockedResult, returnedResult));
    }
}
