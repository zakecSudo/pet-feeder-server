package com.degree.petFeeder.scheduler;

import com.degree.petFeeder.configuration.MqttPublisher;
import com.degree.petFeeder.model.Schedule;
import com.degree.petFeeder.repository.ScheduleRepository;
import com.degree.petFeeder.utils.MotorUtils;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MqttScheduler {

    private static final Logger logger = LoggerFactory.getLogger(MqttScheduler.class);

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    MqttPublisher mqttPublisher;


    // Scheduler starts every second in multiple threads if necessary
    @Async
    @Scheduled(fixedRate = 1000)
    public void startFeedings() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Schedule> schedules = scheduleRepository.findByActiveAndTimeAndRepeatDaysContains(true, dateTime.toLocalTime(),
                Schedule.Day.valueOf(dateTime.getDayOfWeek().name()));

        for (Schedule schedule : schedules) {
            try {
                mqttPublisher.turnMotor(MotorUtils.calculateTurnsFromSeconds(schedule.getFeeding().getDurationSeconds()));
                logger.error(String.format("Turned motor for %.3f turns", schedule.getFeeding().getDurationSeconds()));
            } catch (MqttException e) {
                logger.error("Failed to turn motor");
            }
        }
    }
}
