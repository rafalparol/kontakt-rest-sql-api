package inc.temp.right.always.restsqlapi.services;

import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import inc.temp.right.always.restsqlapi.services.custom.CustomAnomaliesPreparedStatementSetter;
import inc.temp.right.always.restsqlapi.services.custom.CustomAnomaliesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomaliesService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static final String QUERY = "SELECT thermometerid AS thermometerId, COUNT(*) AS anomaliesCount FROM anomalies GROUP BY thermometerId HAVING COUNT(*) > ?";
    @Retryable
    public List<AnomaliesCount> findAllWithAnomaliesCountBiggerThan(int threshold) {
        return jdbcTemplate.query(QUERY,
            new CustomAnomaliesPreparedStatementSetter(threshold), new CustomAnomaliesRowMapper()
        );
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
