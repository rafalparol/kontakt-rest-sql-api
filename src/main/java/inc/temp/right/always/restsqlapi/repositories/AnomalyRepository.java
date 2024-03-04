package inc.temp.right.always.restsqlapi.repositories;

import inc.temp.right.always.restsqlapi.model.MeasurementId;
import inc.temp.right.always.restsqlapi.model.TemperatureMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public interface AnomalyRepository extends JpaRepository<TemperatureMeasurement, MeasurementId> {
    List<TemperatureMeasurement> findAllByRoomId(String roomId);
    List<TemperatureMeasurement> findAllByMeasurementIdThermometerId(String thermometerId);
    @Query("SELECT a FROM TemperatureMeasurement a WHERE a.measurementId.measuredAt >= :start AND a.measurementId.measuredAt <= :end")
    List<TemperatureMeasurement> findAllBetweenStartDateAndEndDate(@Param("start") Timestamp start, @Param("end") Timestamp end);
}
