package inc.temp.right.always.restsqlapi;

import inc.temp.right.always.restsqlapi.controllers.AnomaliesController;
import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import inc.temp.right.always.restsqlapi.model.MeasurementId;
import inc.temp.right.always.restsqlapi.model.TemperatureMeasurement;
import inc.temp.right.always.restsqlapi.repositories.AnomalyRepository;
import inc.temp.right.always.restsqlapi.services.AnomaliesService;
import inc.temp.right.always.restsqlapi.util.DateAndTimeConversionManager;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AnomaliesControllerTests {
    @Test
    public void getAnomaliesByRoom_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyRepository anomalyRepository = mock(AnomalyRepository.class);
        ArrayList<TemperatureMeasurement> mockedResult = new ArrayList<TemperatureMeasurement>();
        mockedResult.add(new TemperatureMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:00")), "room-1", 31.0));
        mockedResult.add(new TemperatureMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-02 00:00:00")), "room-1", 32.0));
        when(anomalyRepository.findAllByRoomId("room-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyRepository(anomalyRepository);

        List<TemperatureMeasurement> returnedResult = anomaliesController.getAnomaliesByRoom("room-1");

        verify(anomalyRepository, times(1)).findAllByRoomId("room-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by room.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByRoom_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyRepository anomalyRepository = mock(AnomalyRepository.class);
        ArrayList<TemperatureMeasurement> mockedResult = new ArrayList<TemperatureMeasurement>();
        when(anomalyRepository.findAllByRoomId("room-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyRepository(anomalyRepository);

        List<TemperatureMeasurement> returnedResult = anomaliesController.getAnomaliesByRoom("room-1");

        verify(anomalyRepository, times(1)).findAllByRoomId("room-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by room.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByThermometer_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyRepository anomalyRepository = mock(AnomalyRepository.class);
        ArrayList<TemperatureMeasurement> mockedResult = new ArrayList<TemperatureMeasurement>();
        mockedResult.add(new TemperatureMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:00")), "room-1", 31.0));
        mockedResult.add(new TemperatureMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-02 00:00:00")), "room-1", 32.0));
        when(anomalyRepository.findAllByMeasurementIdThermometerId("thermometer-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyRepository(anomalyRepository);

        List<TemperatureMeasurement> returnedResult = anomaliesController.getAnomaliesByThermometer("thermometer-1");

        verify(anomalyRepository, times(1)).findAllByMeasurementIdThermometerId("thermometer-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by thermometer.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByThermometer_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyRepository anomalyRepository = mock(AnomalyRepository.class);
        ArrayList<TemperatureMeasurement> mockedResult = new ArrayList<TemperatureMeasurement>();
        when(anomalyRepository.findAllByMeasurementIdThermometerId("thermometer-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyRepository(anomalyRepository);

        List<TemperatureMeasurement> returnedResult = anomaliesController.getAnomaliesByThermometer("thermometer-1");

        verify(anomalyRepository, times(1)).findAllByMeasurementIdThermometerId("thermometer-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by thermometer.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByTime_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyRepository anomalyRepository = mock(AnomalyRepository.class);
        ArrayList<TemperatureMeasurement> mockedResult = new ArrayList<TemperatureMeasurement>();
        mockedResult.add(new TemperatureMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:00")), "room-1", 31.0));
        mockedResult.add(new TemperatureMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-02 00:00:00")), "room-1", 32.0));
        when(anomalyRepository.findAllBetweenStartDateAndEndDate(Timestamp.valueOf("2024-01-01 00:00:00"), Timestamp.valueOf("2024-03-01 00:00:00"))).thenReturn(mockedResult);

        DateAndTimeConversionManager dateAndTimeConversionManager = mock(DateAndTimeConversionManager.class);
        when(dateAndTimeConversionManager.fromStringDateToTimestamp("2024-01-01")).thenReturn(Timestamp.valueOf("2024-01-01 00:00:00"));
        when(dateAndTimeConversionManager.fromStringDateToTimestamp("2024-03-01")).thenReturn(Timestamp.valueOf("2024-03-01 00:00:00"));

        anomaliesController.setAnomalyRepository(anomalyRepository);
        anomaliesController.setDateAndTimeConversionManager(dateAndTimeConversionManager);

        List<TemperatureMeasurement> returnedResult = anomaliesController.getAnomaliesByTime("2024-01-01", "2024-03-01");

        verify(dateAndTimeConversionManager, times(1)).fromStringDateToTimestamp("2024-01-01");
        verify(dateAndTimeConversionManager, times(1)).fromStringDateToTimestamp("2024-03-01");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by time.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByTime_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyRepository anomalyRepository = mock(AnomalyRepository.class);
        ArrayList<TemperatureMeasurement> mockedResult = new ArrayList<TemperatureMeasurement>();
        when(anomalyRepository.findAllBetweenStartDateAndEndDate(Timestamp.valueOf("2024-01-01 00:00:00"), Timestamp.valueOf("2024-03-01 00:00:00"))).thenReturn(mockedResult);

        DateAndTimeConversionManager dateAndTimeConversionManager = mock(DateAndTimeConversionManager.class);
        when(dateAndTimeConversionManager.fromStringDateToTimestamp("2024-01-01")).thenReturn(Timestamp.valueOf("2024-01-01 00:00:00"));
        when(dateAndTimeConversionManager.fromStringDateToTimestamp("2024-03-01")).thenReturn(Timestamp.valueOf("2024-03-01 00:00:00"));

        anomaliesController.setAnomalyRepository(anomalyRepository);
        anomaliesController.setDateAndTimeConversionManager(dateAndTimeConversionManager);

        List<TemperatureMeasurement> returnedResult = anomaliesController.getAnomaliesByTime("2024-01-01", "2024-03-01");

        verify(dateAndTimeConversionManager, times(1)).fromStringDateToTimestamp("2024-01-01");
        verify(dateAndTimeConversionManager, times(1)).fromStringDateToTimestamp("2024-03-01");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by time.", mockedResult, returnedResult));
    }

    @Test
    public void getThermometersWithAnomaliesCountBiggerThan_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomaliesService anomalyService = mock(AnomaliesService.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        mockedResult.add(new AnomaliesCount("thermometer-1", 5));
        mockedResult.add(new AnomaliesCount("thermometer-2", 2));
        when(anomalyService.findAllWithAnomaliesCountBiggerThan(1)).thenReturn(mockedResult);

        anomaliesController.setAnomaliesService(anomalyService);

        List<AnomaliesCount> returnedResult = anomaliesController.getThermometersWithAnomaliesCountBiggerThan(1);

        verify(anomalyService, times(1)).findAllWithAnomaliesCountBiggerThan(1);
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting thermometers with anomalies.", mockedResult, returnedResult));
    }

    @Test
    public void getThermometersWithAnomaliesCountBiggerThan_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomaliesService anomalyService = mock(AnomaliesService.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        when(anomalyService.findAllWithAnomaliesCountBiggerThan(10)).thenReturn(mockedResult);

        anomaliesController.setAnomaliesService(anomalyService);

        List<AnomaliesCount> returnedResult = anomaliesController.getThermometersWithAnomaliesCountBiggerThan(10);

        verify(anomalyService, times(1)).findAllWithAnomaliesCountBiggerThan(10);
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting thermometers with anomalies.", mockedResult, returnedResult));
    }
}
