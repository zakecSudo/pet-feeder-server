package com.degree.petFeeder.repository;

import com.degree.petFeeder.model.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByActiveAndTimeAndRepeatDaysContains(boolean active, LocalTime time, Schedule.Day day);

    List<Schedule> findByActive(boolean active, Sort sort);

    List<Schedule> findByActiveAndRepeatDaysNotEmpty(boolean active, Sort sort);
}
