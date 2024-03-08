package inc.temp.right.always.restsqlapi.services.custom;

import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class CustomAnomaliesRowMapper implements RowMapper<AnomaliesCount> {
    @Override
    public AnomaliesCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnomaliesCount anomaliesCount = new AnomaliesCount();
        anomaliesCount.setThermometerId(rs.getString("thermometerId"));
        anomaliesCount.setAnomalies(rs.getLong("anomaliesCount"));
        return anomaliesCount;
    }
}
