package com.degree.petFeeder.repository;

import com.degree.petFeeder.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByActiveAndTimeAndRepeatDaysContains(boolean active, LocalTime time, Schedule.Day day);
}
