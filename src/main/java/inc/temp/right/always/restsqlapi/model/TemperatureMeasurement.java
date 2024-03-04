package inc.temp.right.always.restsqlapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="anomalies")
public class TemperatureMeasurement {
    @EmbeddedId
    private MeasurementId measurementId;
    @Column(name = "roomid")
    private String roomId;
    @Column(name = "temperature")
    private double temperature;
}
