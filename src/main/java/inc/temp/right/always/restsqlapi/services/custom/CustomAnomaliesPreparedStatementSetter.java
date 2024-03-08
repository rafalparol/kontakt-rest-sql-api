package inc.temp.right.always.restsqlapi.services.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomAnomaliesPreparedStatementSetter implements PreparedStatementSetter {
    private int threshold;
    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        ps.setLong(1, threshold);
    }
}
