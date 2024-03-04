package inc.temp.right.always.restsqlapi.services;

import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomaliesService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String QUERY = "SELECT thermometerid AS thermometerId, COUNT(*) AS anomaliesCount FROM anomalies GROUP BY thermometerId HAVING COUNT(*) > ?";
    public List<AnomaliesCount> findAllWithAnomaliesCountBiggerThan(int threshold) {
        return jdbcTemplate.query(QUERY,
            ps -> ps.setLong(1, threshold),
            (rs, rowNum) -> {
                AnomaliesCount anomaliesCount = new AnomaliesCount();
                anomaliesCount.setThermometerId(rs.getString("thermometerId"));
                anomaliesCount.setAnomalies(rs.getLong("anomaliesCount"));
                return anomaliesCount;
            }
        );
    }
}
