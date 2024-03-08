package inc.temp.right.always.restsqlapi.controllers;

import inc.temp.right.always.restsqlapi.dto.AnomaliesCount;
import inc.temp.right.always.restsqlapi.model.TemperatureMeasurement;
import inc.temp.right.always.restsqlapi.repositories.AnomalyRepository;
import inc.temp.right.always.restsqlapi.services.AnomaliesService;
import inc.temp.right.always.restsqlapi.util.DateAndTimeConversionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AnomaliesController {
    @Autowired
    private AnomalyRepository anomalyRepository;
    @Autowired
    private AnomaliesService anomaliesService;
    @Autowired
    private DateAndTimeConversionManager dateAndTimeConversionManager;

    @GetMapping("/anomaliesByRoom/{roomId}")
    @ResponseBody
    public List<TemperatureMeasurement> getAnomaliesByRoom(@PathVariable("roomId") String roomId) {
        return anomalyRepository.findAllByRoomId(roomId);
    }

    @GetMapping("/anomaliesByThermometer/{thermometerId}")
    @ResponseBody
    public List<TemperatureMeasurement> getAnomaliesByThermometer(@PathVariable("thermometerId") String thermometerId) {
        return anomalyRepository.findAllByMeasurementIdThermometerId(thermometerId);
    }

    @GetMapping("/anomaliesByTime/start/{start}/end/{end}")
    @ResponseBody
    public List<TemperatureMeasurement> getAnomaliesByTime(@PathVariable("start") String start, @PathVariable("end") String end) {
        return anomalyRepository.findAllBetweenStartDateAndEndDate(dateAndTimeConversionManager.fromStringDateToTimestamp(start), dateAndTimeConversionManager.fromStringDateToTimestamp(end));
    }

    @GetMapping("/anomalies/{threshold}")
    @ResponseBody
    public List<AnomaliesCount> getThermometersWithAnomaliesCountBiggerThan(@PathVariable("threshold") int threshold) {
        return anomaliesService.findAllWithAnomaliesCountBiggerThan(threshold);
    }

    public void setAnomalyRepository(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    public void setAnomaliesService(AnomaliesService anomaliesService) {
        this.anomaliesService = anomaliesService;
    }

    public void setDateAndTimeConversionManager(DateAndTimeConversionManager dateAndTimeConversionManager) {
        this.dateAndTimeConversionManager = dateAndTimeConversionManager;
    }
}
